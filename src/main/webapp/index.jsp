<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechRepair - Dashboard</title>
    <link rel="stylesheet" href="./assets/style.css">
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
</head>
<body>
<div class="container">
    <nav class="sidebar">
        <div class="logo">
            <i class="fas fa-laptop-medical"></i>
            <span>TechRepair</span>
        </div>

        <ul class="nav-menu">
            <li class="nav-item">
                <a href="index.jsp" class="nav-link active">
                    <i class="fas fa-home"></i>
                    <span>Tableau de bord</span>
                </a>
            </li>

            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-users"></i>
                    <span>Clients</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href="#">Nouveau client</a></li>
                    <li><a href="#">Liste des clients</a></li>
                    <li><a href="#">Historique</a></li>
                </ul>
            </li>

            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-tools"></i>
                    <span>Reparations</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href="insert.html">Nouvelle réparation</a></li>
                    <li><a href="typeProbleme">Liste ordinateur par probleme</a></li>
                    <li><a href="liste.html">Historique</a></li>

                </ul>
            </li>

            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-box"></i>
                    <span>Stock</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href="#">Inventaire</a></li>
                    <li><a href="#">Commandes</a></li>
                    <li><a href="#">Fournisseurs</a></li>
                </ul>
            </li>

            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-file-invoice-dollar"></i>
                    <span>Facturation</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href="#">Nouvelle facture</a></li>
                    <li><a href="#">Historique</a></li>
                    <li><a href="#">Rapports</a></li>
                </ul>
            </li>
        </ul>
    </nav>

    <main class="main-content">
        <!-- Rest of the content remains the same -->
        <header class="top-bar">
            <div class="search-bar">
                <i class="fas fa-search"></i>
                <input type="text" placeholder="Rechercher...">
            </div>
            <div class="user-menu">
                <i class="fas fa-bell"></i>
                <i class="fas fa-user-circle"></i>
            </div>
        </header>

        <div class="dashboard-content">
            <h1>Tableau de bord</h1>

            <div class="stats-grid">
                <div class="stat-card">
                    <i class="fas fa-wrench"></i>
                    <div class="stat-info">
                        <h3>Réparations en cours</h3>
                        <p>12</p>
                    </div>
                </div>
                <div class="stat-card">
                    <i class="fas fa-check-circle"></i>
                    <div class="stat-info">
                        <h3>Terminées aujourd'hui</h3>
                        <p>8</p>
                    </div>
                </div>
                <div class="stat-card">
                    <i class="fas fa-clock"></i>
                    <div class="stat-info">
                        <h3>En attente</h3>
                        <p>5</p>
                    </div>
                </div>
                <div class="stat-card">
                    <i class="fas fa-euro-sign"></i>
                    <div class="stat-info">
                        <h3>Revenus du jour</h3>
                        <p>1250 €</p>
                    </div>
                </div>
            </div>
        </div>
    </main>
</div>
</body>
</html>