<%--
  Created by IntelliJ IDEA.
  User: Mahery
  Date: 08/01/2025
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechRepair - Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        /* Add these styles to your existing CSS file */

        /* Form Styles */
        .repair-form {
            background-color: var(--card-bg);
            padding: 2rem;
            border-radius: 16px;
            box-shadow: var(--shadow);
        }

        .form-grid {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 1.5rem;
            margin-bottom: 1.5rem;
        }

        .form-group {
            display: flex;
            flex-direction: column;
            gap: 0.5rem;
        }

        .form-group.full-width {
            grid-column: 1 / -1;
            margin-bottom: 1.5rem;
        }

        .form-group label {
            color: #1e293b;
            font-weight: 500;
            font-size: 0.95rem;
        }

        .form-group input,
        .form-group select,
        .form-group textarea {
            padding: 0.75rem;
            border: 1px solid #e2e8f0;
            border-radius: 8px;
            background-color: #fff;
            color: #1e293b;
            font-size: 0.95rem;
            transition: all 0.2s ease;
        }

        .form-group input:focus,
        .form-group select:focus,
        .form-group textarea:focus {
            border-color: var(--primary-color);
            box-shadow: 0 0 0 3px rgba(79, 70, 229, 0.1);
            outline: none;
        }

        .form-group textarea {
            resize: vertical;
            min-height: 100px;
        }

        .form-actions {
            display: flex;
            justify-content: flex-end;
            gap: 1rem;
            margin-top: 2rem;
        }

        .btn {
            padding: 0.75rem 1.5rem;
            border-radius: 8px;
            font-weight: 500;
            font-size: 0.95rem;
            transition: all 0.2s ease;
            cursor: pointer;
            border: none;
        }

        .btn-primary {
            background-color: var(--primary-color);
            color: white;
        }

        .btn-primary:hover {
            background-color: var(--secondary-color);
            transform: translateY(-1px);
        }

        .btn-secondary {
            background-color: #e2e8f0;
            color: #1e293b;
        }

        .btn-secondary:hover {
            background-color: #cbd5e1;
            transform: translateY(-1px);
        }

        @media (max-width: 768px) {
            .repair-form {
                padding: 1.5rem;
            }

            .form-actions {
                flex-direction: column-reverse;
            }

            .btn {
                width: 100%;
            }
        }
        .success-message {
            background-color: #d4edda;  /* couleur de fond verte */
            border: 1px solid #c3e6cb;  /* bordure verte claire */
            color: #155724;  /* texte vert foncé */
            padding: 10px;
            border-radius: 5px;
            margin-bottom: 15px;
            font-size: 1rem;
            box-shadow: 0 2px 5px rgba(0, 0, 0, 0.1);
        }

    </style>
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
                <a href="index.jsp" class="nav-link">
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
                <a href="#" class="nav-link active">
                    <i class="fas fa-industry"></i>
                    <span>Marque</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href=<%= request.getContextPath()+"/modeleServlet"%>>Nouvelle marque</a></li>
                    <li><a href=<%= request.getContextPath()+"/modeleListServlet"%>>Liste des marques</a></li>
                </ul>
            </li>
            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-tag"></i>
                    <span>Modele</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href="pages/modele/insert.jsp">Nouveau modele</a></li>
                    <li><a href=<%= request.getContextPath()+"/modeleServlet"%>>Liste des modeles</a></li>
                </ul>
            </li>
            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-desktop"></i>
                    <span>Ordinateur</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href=<%= request.getContextPath()+"/ordinateur"%>>Nouvel ordinateur</a></li>
                    <li><a href=<%= request.getContextPath()+"/ordinateurListServlet"%>>Liste des ordinateurs</a></li>
                </ul>
            </li>

            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-tools"></i>
                    <span>Réparations</span>
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
        <div class="dashboard-content">
            <h1>Nouvelle Marque</h1>
            <%
                String message = (String) request.getAttribute("message");
                if (message != null && !message.isEmpty()) {
            %>
            <div class="alert alert-success success-message">
                <strong>Succès!</strong> <%= message %>
            </div>
            <%
                }
            %>

            <form class="repair-form" method="post" action=<%=request.getContextPath()+"/marqueServlet"%>>
                <div class="form-grid">
                    <div class="form-group">
                        <label for="client">Marque</label>
                        <input type="text" id="client" placeholder="Nom de la marque" name="nom">
                    </div>
                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Créer la marque</button>
                </div>
            </form>
        </div>
    </main>
</div>
</body>
</html>
