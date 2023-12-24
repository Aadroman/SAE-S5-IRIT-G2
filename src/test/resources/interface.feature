#En tant qu’utilisateur je peux rentrer une requete SQL dans l’application java et obtenir des arbres algébriques logiques. (priorité haute)
#
#En tant qu’utilisateur j’intéragis avec une vue logique qui me permet de voir l’entièreté des données, même si elles sont stockées dans des bases différentes (priorité haute)
#
#En tant qu’utilisateur je peux visualiser les 3 formes de l’arbre algébrique logique afin de voir la provenance des données des différentes bases de données.(priorité basse)
#
#En tant qu’utilisateur je peux visualiser les 3 formes de l’arbre algébrique logique afin de voir la nature de chacune des données ( sql / nosql ).(priorité moyenne)
#
#En tant qu’utilisateur je peux zoomer et me déplacer sur l’arbre algébrique logique généré afin de visualiser l’arbre comme bon me semble. (priorité moyenne)

#En tant qu’utilisateur je peux cliquer sur les éléments de l’arbre algébrique logique pour générer des sous-requetes. (priorité basse)

Feature: testing interface :

  As a user, i want to get a tree based on my query

  Scenario: Getting an empty tree
    Given an empty query
    When writing down query
    Then alert dialog should show up because of an exception

  Scenario: Getting a tree and the application working
    Given a written query
    When writing down query
    Then numerous elements of the application should work and the query can give a tree with the algebraic tree creation program