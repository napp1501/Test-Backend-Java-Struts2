<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Item List</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">
    <link rel="stylesheet" href="https://cdn.datatables.net/2.3.4/css/dataTables.dataTables.css">
    <script src="https://code.jquery.com/jquery-3.7.1.min.js"></script>
    <script src="https://cdn.datatables.net/2.3.4/js/dataTables.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/js/bootstrap.bundle.min.js"></script>

    <style>

        body{
            background:#f4f6f9;
            font-family:'Segoe UI',sans-serif;
        }

        .card{
            border:none;
            border-radius:15px;
        }

        .card-header{
            background:white;
            border-bottom:1px solid #e9ecef;
        }

        table.dataTable{
            border-collapse:collapse !important;
        }

        table.dataTable thead th{
            background:#212529;
            color:white;
            padding:15px;
        }

        table.dataTable tbody td{
            padding:14px;
        }

        table.dataTable tbody tr:hover{
            background:#f8f9fa;
        }

        .badge{
            font-size:14px;
            padding:8px 12px;
            border-radius:8px;
        }

        .dataTables_filter input{
            border-radius:8px;
            border:1px solid #ced4da;
            padding:6px 10px;
        }

        .dataTables_length select{
            border-radius:8px;
        }

    </style>

</head>

<body>

<div class="container mt-5">

    <div class="card shadow">

        <div class="card-header py-4 d-flex justify-content-between align-items-center">

            <h2 class="fw-bold mb-1">
                <i class="bi bi-cart-check"></i>
                Order
            </h2>

            <button type="button"
                    class="btn btn-success"
                    data-bs-toggle="modal"
                    data-bs-target="#add">
                Add
            </button>

            <a href="main.action" class="btn btn-warning">
                <i class="bi bi-arrow-left"></i> Back
            </a>

        </div>

        <div class="card-body">

            <table id="orderTable" class="table table-hover align-middle">

                <thead>
                <tr>
                    <th class="text-center" width="10%">No</th>
                    <th class="text-center" width="15%">Order No</th>
                    <th class="text-center" width="35%">Item Name</th>
                    <th class="text-center" width="10%">QTY</th>
                    <th class="text-center" width="25%">Action</th>
                </tr>
                </thead>

                <tbody>

                <s:iterator value="orders" status="i">

                    <tr>

                        <td class="text-center">
                            <strong>
                                <s:property value="#i.index + 1"/>
                            </strong>
                        </td>

                        <td class="text-center">
                            <strong>
                                <s:property value="order_no"/>
                            </strong>
                        </td>

                        <td class="text-center">
                            <i class="bi bi-box text-secondary me-2"></i>
                            <strong>
                                <s:property value="name"/>
                            </strong>
                        </td>

                        <td class="text-center">
                            <strong>
                                <s:property value="qty"/>
                            </strong>
                        </td>

                        <td class="text-center">
                            <button type="button"
                                    class="btn btn-primary"
                                    data-bs-toggle="modal"
                                    data-bs-target="#edit"
                                    onclick="editOr(
                                            '<s:property value="id"/>',
                                            '<s:property value="order_no"/>',
                                            '<s:property value="item_id"/>',
                                            '<s:property value="qty"/>'
                                            )">
                                Edit
                            </button>
                            <button
                                    type="button"
                                    class="btn btn-danger"
                                    onclick="deleteAlert(<s:property value='id'/>)">
                                Delete
                            </button>
                            <s:form id="deleteForm_%{id}" action="deleteOrder">
                                <s:hidden name="id" value="%{id}"/>
                            </s:form>
                        </td>

                    </tr>

                </s:iterator>

                </tbody>

            </table>

        </div>

    </div>

    <div class="modal fade" id="add" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <h1 class="modal-title fs-5" id="staticBackdropLabelEdit">Add Order</h1>

                <s:form action="saveOrder" theme="simple">
                    <div class="modal-header">

                    </div>

                    <div class="modal-body">
                        <label class="mt-3">Name</label>
                        <s:select cssClass="form-select"
                                  label="NameItem"
                                  name="item_id"
                                  list="items"
                                  listKey="id"
                                  listValue="name"
                                  headerKey=""
                                  headerValue="Select Item" required="true">
                        </s:select>

                        <label class="mt-3">QTY</label>
                        <s:textfield name="qty" cssClass="form-control" required="true"/>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-secondary"
                                type="button"
                                data-bs-dismiss="modal">
                            Close
                        </button>

                        <s:submit cssClass="btn btn-primary"
                                  value="Confirm"/>
                    </div>
                </s:form>

            </div>
        </div>
    </div>

    <div class="modal fade" id="edit" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content">
                <h1 class="modal-title fs-5" id="staticBackdropLabelEdit">Edit Inventory</h1>

                <s:form action="editOrder" theme="simple">
                    <div class="modal-header">

                    </div>

                    <div class="modal-body">
                        <s:hidden name="id" id="editId"/>
                        <label class="mt-3">Order No</label>
                        <s:textfield name="order_no" cssClass="form-control" id="editOrderNo" disabled="true"/>

                        <label class="mt-3">Name</label>
                        <s:select cssClass="form-select"
                                  label="NameItem"
                                  id="editItemId"
                                  name="item_id"
                                  list="items"
                                  listKey="id"
                                  listValue="name"
                                  headerKey=""
                                  headerValue="Select Item" required="true" >
                        </s:select>

                        <label class="mt-3">QTY</label>
                        <s:textfield name="qty" cssClass="form-control" id="editQTY" required="true"/>
                    </div>

                    <div class="modal-footer">
                        <button class="btn btn-secondary"
                                type="button"
                                data-bs-dismiss="modal">
                            Close
                        </button>

                        <s:submit cssClass="btn btn-primary"
                                  value="Confirm"/>
                    </div>
                </s:form>

            </div>
        </div>
    </div>

</div>

<script>

    $(document).ready(function(){

        $('#orderTable').DataTable({

            pageLength:5,

            lengthMenu:[
                [5,10,25,50,-1],
                [5,10,25,50,"All"]
            ],

            language:{
                search:"Search :",
                lengthMenu:"Show _MENU_ data",
                paginate:{
                    previous:"←",
                    next:"→"
                }
            }

        });

    });
    function deleteAlert(id){
        const swalWithBootstrapButtons = Swal.mixin({
            customClass: {
                confirmButton: "btn btn-success",
                cancelButton: "btn btn-danger"
            },
            buttonsStyling: false
        });
        swalWithBootstrapButtons.fire({
            title: "Are you sure?",
            text: "You won't be able to revert this!",
            icon: "warning",
            showCancelButton: true,
            confirmButtonText: "Yes, delete it!",
            cancelButtonText: "No, cancel!",
            reverseButtons: true
        }).then((result) => {
            if (result.isConfirmed) {
                $("#deleteForm_" + id).submit();
                swalWithBootstrapButtons.fire({
                    title: "Deleted!",
                    text: "Your file has been deleted.",
                    icon: "success"
                });
            }else if (result.dismiss === Swal.DismissReason.cancel)
                /* Read more about handling dismissals below */
                swalWithBootstrapButtons.fire({
                    title: "Cancelled",
                    text: "Your imaginary file is safe :)",
                    icon: "error"
                });
        });
    }

    function editOr(id, orderNo, itemId, qty) {
        $("#editId").val(id);
        $("#editOrderNo").val(orderNo);
        $("#editItemId").val(itemId);
        $("#editQTY").val(qty);
    }

</script>

<s:if test="hasActionErrors()">
    <script>
        Swal.fire({
            icon: "error",
            title: "Oops...",
            text: "<s:property value='actionErrors.iterator().next()' escapeJavaScript='true'/>"
        });
    </script>
</s:if>

</body>
</html>