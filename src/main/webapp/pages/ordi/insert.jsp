<%--
  Created by IntelliJ IDEA.
  User: Mahery
  Date: 08/01/2025
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="entity.Marque" %>
<%@ page import="java.util.List" %>
<%@ page import="entity.TypeOrdinateur" %>

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
                <a href="#" class="nav-link">
                    <i class="fas fa-industry"></i>
                    <span>Marque</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href="pages/marque/insert.jsp">Nouvelle marque</a></li>
                    <li><a href=<%= request.getContextPath()+"/marqueServlet"%>>Liste des marques</a></li>
                </ul>
            </li>
            <li class="nav-item has-submenu">
                <a href="#" class="nav-link">
                    <i class="fas fa-tag"></i>
                    <span>Modele</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <li><a href=<%= request.getContextPath()+"/modeleServlet"%>>Nouveau modele</a></li>
                    <li><a href=<%= request.getContextPath()+"/modeleListServlet"%>>Liste des modeles</a></li>
                </ul>
            </li>
            <li class="nav-item has-submenu">
                <a href="#" class="nav-link active">
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
            <h1>Nouvel ordinateur</h1>
            <%
                List<Marque> marques = (List<Marque>) request.getAttribute("marques");
                List<TypeOrdinateur> types = (List<TypeOrdinateur>) request.getAttribute("types");
                String message = (String) request.getAttribute("message");
            %>

            <% if (message != null && !message.isEmpty()) { %>
            <div class="alert alert-success success-message">
                <strong>Succès!</strong> <%= message %>
            </div>
            <% } %>

            <form class="repair-form" method="post" action="<%=request.getContextPath() %>/ordinateurServlet">
                <div class="form-grid">
                    <div class="form-group">
                        <label for="marque">Marque</label>
                        <select id="marque" name="idMarque" onchange="updateModeles()">
                            <option hidden>Choisir</option>
                            <% for (Marque m : marques) { %>
                            <option value="<%= m.getId() %>"><%= m.getNom() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="modele">Modèle</label>
                        <select id="modele" name="idModele"></select>
                    </div>

                    <div class="form-group">
                        <label for="type">Type</label>
                        <select id="type" name="idType">
                            <% for (TypeOrdinateur type : types) { %>
                            <option value="<%= type.getId() %>"><%= type.getNom() %></option>
                            <% } %>
                        </select>
                    </div>

                    <div class="form-group">
                        <label for="date">Date</label>
                        <input type="datetime-local" id="date" name="date">
                    </div>
                </div>

                <div class="form-actions">
                    <button type="submit" class="btn btn-primary">Enregistrer l'ordinateur</button>
                </div>
            </form>
        </div>
    </main>
</div>

<script>
    function updateModeles() {
        const marqueId = document.getElementById('marque').value;
        const modeleSelect = document.getElementById('modele');

        // Effacer les options actuelles
        modeleSelect.innerHTML = '<option value="">Chargement...</option>';

        // Appeler le servlet pour récupérer les modèles
        fetch(`<%= request.getContextPath() %>/modeleByMarque?idMarque=`+marqueId)
            .then(response => response.json())
            .then(data => {
                modeleSelect.innerHTML = '<option value="">Sélectionnez un modèle</option>';
                data.forEach(modele => {
                    const option = document.createElement('option');
                    option.value = modele.id;
                    option.textContent = modele.nom;
                    modeleSelect.appendChild(option);
                });
            })
            .catch(error => {
                console.error('Erreur lors du chargement des modèles:', error);
                modeleSelect.innerHTML = '<option value="">Erreur</option>';
            });
    }
</script>
</body>
</html>
