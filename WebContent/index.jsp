<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE html>
<html>
<head>

    <meta charset="UTF-8">
    <title>Bananapp Store</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">

    <link rel="stylesheet"
          href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.11.3/font/bootstrap-icons.min.css">

    <style>

        body{
            background:#f5f7fb;
            font-family:'Segoe UI',sans-serif;
        }

        .header{
            background:linear-gradient(135deg,#0d6efd,#3b82f6);
            color:white;
            padding:50px 0;
            text-align:center;
            margin-bottom:50px;
            box-shadow:0 5px 15px rgba(0,0,0,.15);
        }

        .header h1{
            font-weight:bold;
        }

        .menu-card{

            background:white;
            border-radius:20px;
            padding:40px 25px;
            text-align:center;
            transition:.3s;
            cursor:pointer;
            height:320px;

            box-shadow:0 10px 25px rgba(0,0,0,.08);

        }

        .menu-card:hover{

            transform:translateY(-10px);

            box-shadow:0 20px 35px rgba(13,110,253,.2);

        }

        .menu-icon{

            width:100px;
            height:100px;
            line-height:100px;

            margin:auto;

            border-radius:50%;

            font-size:48px;

            color:white;

            margin-bottom:25px;

        }

        .item{
            background:#0d6efd;
        }

        .order{
            background:#198754;
        }

        .inventory{
            background:#fd7e14;
        }

        .report{
            background:#6f42c1;
        }

        .menu-card h3{

            font-weight:bold;
            margin-bottom:15px;

        }

        .menu-card p{

            color:#6c757d;
            min-height:55px;

        }

        .menu-btn{

            border-radius:30px;

            padding:10px 30px;

        }

        a{

            text-decoration:none;

        }

    </style>

</head>

<body>

<div class="header">

    <h1>
        <i class="bi bi-grid-fill"></i>
        Bananapp Store
    </h1>

</div>

<div class="container">

    <div class="row g-4">

        <div class="col-md-3">

            <a href="item.action">

                <div class="menu-card">

                    <div class="menu-icon item">
                        <i class="bi bi-box-seam"></i>
                    </div>

                    <h3>Item</h3>

                    <p>
                        Kelola data master item yang tersedia.
                    </p>

                    <button class="btn btn-primary menu-btn">

                        Open

                    </button>

                </div>

            </a>

        </div>

        <div class="col-md-3">

            <a href="order.action">

                <div class="menu-card">

                    <div class="menu-icon order">
                        <i class="bi bi-cart-check"></i>
                    </div>

                    <h3>Order</h3>

                    <p>
                        Kelola transaksi pemesanan barang.
                    </p>

                    <button class="btn btn-success menu-btn">

                        Open

                    </button>

                </div>

            </a>

        </div>

        <div class="col-md-3">

            <a href="inventory.action">

                <div class="menu-card">

                    <div class="menu-icon inventory">
                        <i class="bi bi-archive-fill"></i>
                    </div>

                    <h3>Inventory</h3>

                    <p>
                        Monitoring stok masuk dan keluar barang.
                    </p>

                    <button class="btn btn-warning text-white menu-btn">

                        Open

                    </button>

                </div>

            </a>

        </div>

        <div class="col-md-3">

            <a href="report.action">

                <div class="menu-card">

                    <div class="menu-icon report">
                        <i class="bi bi-file-earmark-bar-graph"></i>
                    </div>

                    <h3>Report</h3>

                    <p>
                        Lihat dan cetak laporan inventory.
                    </p>

                    <button class="btn btn-secondary menu-btn">

                        Open

                    </button>

                </div>

            </a>

        </div>

    </div>

</div>

</body>
</html>