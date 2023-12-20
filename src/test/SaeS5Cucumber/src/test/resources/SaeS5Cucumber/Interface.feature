En tant qu’utilisateur je peux rentrer une requete SQL dans l’application java et obtenir des arbres algébriques logiques. (priorité haute)

En tant qu’utilisateur j’intéragis avec une vue logique qui me permet de voir l’entièreté des données, même si elles sont stockées dans des bases différentes (priorité haute)

En tant qu’utilisateur je peux visualiser les 3 formes de l’arbre algébrique logique afin de voir la provenance des données des différentes bases de données.(priorité basse)

En tant qu’utilisateur je peux visualiser les 3 formes de l’arbre algébrique logique afin de voir la nature de chacune des données ( sql / nosql ).(priorité moyenne)

En tant qu’utilisateur je peux zoomer et me déplacer sur l’arbre algébrique logique généré afin de visualiser l’arbre comme bon me semble. (priorité moyenne)

En tant qu’utilisateur je peux cliquer sur les éléments de l’arbre algébrique logique pour générer des sous-requetes. (priorité basse)

Feature: Cocktail Ordering

  As Romeo, I want to offer a drink to Juliette so that we can discuss together (and maybe more).

  Scenario: Creating an empty order
    Given Romeo who wants to buy a drink
    When  an order is declared for Juliette
    Then  there is no cocktail in the order

Feature: Creating algebraic logic trees :

    As a user, i can enter a SQL Query in a java application to get algebraic logic trees 

    Scenario : Getting an empty tree
        Given an empty query
        When nodes are created when the tree is created
        Then the tree doesn't have nodes

