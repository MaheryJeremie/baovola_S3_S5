<%@ page import="java.util.List" %>

<%@ page import="java.util.HashSet" %>
<%@ page import="java.util.Set" %>
<%@ page import="entity.*" %>
<!DOCTYPE html>
<html lang="fr">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>TechRepair - Dashboard</title>
    <link rel="stylesheet" href="<%= request.getContextPath() %>/assets/style.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css">
    <style>
        /* Table Styles */
        .table-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 2rem;
        }

        .table-container {
            background-color: var(--card-bg);
            border-radius: 16px;
            box-shadow: var(--shadow);
            overflow: auto;
        }

        .repair-table {
            width: 100%;
            border-collapse: collapse;
            white-space: nowrap;
        }

        .repair-table th,
        .repair-table td {
            padding: 1rem;
            text-align: left;
            border-bottom: 1px solid #e2e8f0;
        }

        .repair-table th {
            background-color: #f8fafc;
            font-weight: 600;
            color: #1e293b;
        }

        .repair-table tr:hover {
            background-color: #f8fafc;
        }
        .top-bar {
            display: flex;
            justify-content: space-between;
            align-items: center;
            margin-bottom: 1.5rem;
            padding: 1rem;
            background-color: var(--card-bg);
            border-radius: 8px;
            box-shadow: var(--shadow);
        }

        .search-bar {
            display: flex;
            align-items: center;
            gap: 1rem;
            width: 100%;
        }

        .search-bar i {
            font-size: 1.2rem;
            color: #1e1b4b;
        }

        .filters {
            display: flex;
            align-items: center;
            gap: 0.75rem;
            width: 100%;
        }

        .filters select,.filterMarque {
            padding: 0.5rem 1rem;
            border: 1px solid #cbd5e1;
            border-radius: 8px;
            background-color: #f8fafc;
            font-size: 0.9rem;
            color: #1e293b;
            transition: border-color 0.2s ease, background-color 0.2s ease;
            appearance: none; /* Hide native dropdown icon */
        }

        .filters select:focus {
            outline: none;
            border-color: var(--primary-color);
            background-color: #ffffff;
        }

        .filters button {
            padding: 0.5rem 1.5rem;
            background-color: #1e1b4b;
            color: white;
            font-size: 0.9rem;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: background-color 0.2s ease, transform 0.2s ease;
        }

        .filters button:hover {
            background-color: var(--secondary-color);
            transform: translateY(-1px);
        }

        .filters button:active {
            transform: translateY(0);
        }


        @media (max-width: 768px) {
            .table-header {
                flex-direction: column;
                gap: 1rem;
            }

            .table-header .btn {
                width: 100%;
            }

            .table-container {
                border-radius: 12px;
            }
            .search-bar {
                flex-direction: column;
                gap: 0.5rem;
            }

            .filters {
                flex-direction: column;
                align-items: stretch;
                gap: 0.5rem;
            }

            .filters select,
            .filters button {
                width: 100%;
            }
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
                    <li><a href="marqueServlet">Liste des marques</a></li>
                </ul>
            </li>

            <li class="nav-item has-submenu">
                <a href="#" class="nav-link" active>
                    <i class="fas fa-tools"></i>
                    <span>Réparations</span>
                    <i class="fas fa-chevron-right"></i>
                </a>
                <ul class="submenu">
                    <ul class="submenu">
                        <li><a href="insert.html">Nouvelle réparation</a></li>
                        <li><a href="typeProbleme">Liste ordinateur par probleme</a></li>
                        <li><a href="liste.html">Historique</a></li>
                    </ul>

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
        <% List<TypeProbleme> listeProblemes = (List<TypeProbleme>) request.getAttribute("listeProblemes"); %>
        <% List<Reparation> listeReparations = (List<Reparation>) request.getAttribute("listeReparations"); %>
        <% List<Etat> listeEtats = (List<Etat>) request.getAttribute("listeEtats"); %>
        <% List<Marque> listeMarques = (List<Marque>) request.getAttribute("listeMarques"); %>
        <% List<TypeOrdinateur> listeTypeOrdi = (List<TypeOrdinateur>) request.getAttribute("listeTypeOrdi"); %>
        <section>
            <h1>Liste des ordinateurs par probleme</h1>
            <header class="top-bar">
                <div class="search-bar">
                    <i class="fas fa-filter"></i>
                    <form action="listeOrdinateur" method="get" class="filters">
                        <div>
                            <select name="idProbleme" id="searchCategory">
                                <option value="">Tous</option>
                                <%
                                    String selected = (String) request.getAttribute("selected");
                                    for (TypeProbleme type : listeProblemes) {
                                %>
                                <option value="<%= type.getId() %>" <%= (selected != null && selected.equals(String.valueOf(type.getId()))) ? "selected" : "" %>>
                                    <%= type.getNom() %>
                                </option>
                                <% } %>
                            </select>

                        </div>

                        <button type="submit">Filtrer</button>
                    </form>
                </div>
            </header>

            <div class="dashboard-content">
                <div class="table-container">
                    <table class="repair-table">
                        <thead>
                        <tr>
                            <th>Modele</th>
                            <th>Marque :
                                <%
                                    Set<String> marques = new HashSet<>();
                                    Set<String> types=new HashSet<>();
                                    for (Marque m : listeMarques) {
                                        marques.add(m.getNom());
                                    }
                                    for (TypeOrdinateur t : listeTypeOrdi){
                                        types.add(t.getNom());
                                    }
                                %>
                                <select id="filterBrand" class="filterMarque" onchange="applyColumnFilter('filterBrand', 1)">
                                    <option value="">Filtrer</option>
                                    <% for (String marque : marques) { %>
                                    <option value="<%= marque %>"><%= marque %></option>
                                    <% } %>
                                </select>
                            </th>
                            <th>Type :
                                <select id="filterType" class="filterMarque" onchange="applyColumnFilter('filterType', 2)">
                                    <option value="">Filtrer</option>
                                    <% for (String type : types) { %>
                                    <option value="<%= type %>"><%= type %></option>
                                    <% } %>
                                </select>
                            </th>
                            <th>Date</th>
                            <th>Etat</th>
                        </tr>
                        </thead>
                        <tbody>
                        <% for(Reparation r : listeReparations){ %>
                        <tr>
                            <td><%=r.getOrdinateur().getModele().getNom()%></td>
                            <td><%=r.getOrdinateur().getMarque().getNom()%></td>
                            <td><%= r.getOrdinateur().getTypeOrdinateur().getNom() %></td>
                            <td><%= r.getOrdinateur().getDateAjout() %></td>
                            <td>
                                <form action="updateEtatReparation">
                                <select>
                                    <input type="number" name="idReparation" value="<%= r.getId() %>" hidden="true">
                                    <option value=""></option>
                                    <% for(Etat e : listeEtats){ %>
                                        <option value="<%= e.getId() %>"><%= e.getNom() %></option>
                                    <% } %>
                                    <input type="submit">
                                </select>
                                </form>
                            </td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
            </div>
    </main>
</div>
<script>
    function applyColumnFilter(selectId, columnIndex) {
        const filterBrandValue = document.getElementById("filterBrand").value.toLowerCase();
        const filterTypeValue = document.getElementById("filterType").value.toLowerCase();
        const table = document.querySelector(".repair-table");
        const rows = table.querySelectorAll("tbody tr");

        rows.forEach(row => {
            const brandCell = row.cells[1].textContent.toLowerCase();
            const typeCell = row.cells[2].textContent.toLowerCase();

            const isBrandMatch = filterBrandValue === "" || brandCell === filterBrandValue;
            const isTypeMatch = filterTypeValue === "" || typeCell === filterTypeValue;

            // Afficher la ligne seulement si les deux filtres sont appliqués correctement
            row.style.display = (isBrandMatch && isTypeMatch) ? "" : "none";
        });
    }
</script>
</body>
</html>