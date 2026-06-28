<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Report Dashboard</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>

        body{
            background:#f5f6fa;
        }

        .summary-card{
            border:none;
            border-radius:15px;
            box-shadow:0 3px 12px rgba(0,0,0,.1);
            transition:.3s;
        }

        .summary-card:hover{
            transform:translateY(-5px);
        }

        .icon{
            font-size:40px;
        }

        .table-card{
            border:none;
            border-radius:15px;
            box-shadow:0 3px 12px rgba(0,0,0,.1);
        }

    </style>

</head>

<body>

<div class="container mt-5">

    <div class="d-flex justify-content-between align-items-center mb-4">

        <h2 class="fw-bold">
            <i class="bi bi-bar-chart-fill"></i>
            Report Dashboard
        </h2>

        <a href="main.action" class="btn btn-warning">
            <i class="bi bi-arrow-left"></i>
            Back
        </a>

    </div>

    <!-- Summary Card -->

    <div class="row mb-4">

        <div class="col-md-4">

            <div class="card summary-card text-center p-4">

                <i class="bi bi-cart-check-fill icon text-primary"></i>

                <h5 class="mt-3">Total Orders</h5>

                <h2>
                    <s:property value="totalOrder"/>
                </h2>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card summary-card text-center p-4">

                <i class="bi bi-box-seam-fill icon text-success"></i>

                <h5 class="mt-3">Total Inventory</h5>

                <h2>
                    <s:property value="totalInventory"/>
                </h2>

            </div>

        </div>

        <div class="col-md-4">

            <div class="card summary-card text-center p-4">

                <i class="bi bi-cash-stack icon text-danger"></i>

                <h5 class="mt-3">Total Revenue</h5>

                <h2>

                    Rp <s:property value="totalRevenue"/>

                </h2>

            </div>

        </div>

    </div>

    <!-- Revenue Table -->

    <div class="card table-card">

        <div class="card-header bg-primary text-white">

            <h5 class="mb-0">

                Revenue Per Item

            </h5>

        </div>

        <div class="card-body">

            <table class="table table-bordered table-hover align-middle text-center">

                <thead class="table-light">

                <tr>

                    <th>No</th>
                    <th>Item Name</th>
                    <th>Price</th>
                    <th>Qty Sold</th>
                    <th>Stock Left</th>
                    <th>Revenue</th>

                </tr>

                </thead>

                <tbody>

                <s:iterator value="reports" status="st">

                    <tr>

                        <td>
                            <s:property value="#st.index+1"/>
                        </td>

                        <td>
                            <s:property value="name"/>
                        </td>

                        <td>

                            Rp <s:property value="price"/>

                        </td>

                        <td>

                            <s:property value="totalOrder"/>

                        </td>

                        <td>

                            <s:property value="stock"/>

                        </td>

                        <td class="fw-bold text-success">

                            Rp <s:property value="revenue"/>

                        </td>

                    </tr>

                </s:iterator>

                </tbody>

            </table>

        </div>

    </div>

</div>

</body>
</html>