---
title: |
  | \vspace{5cm} Rapport de laboratoire
subtitle: Programmation Orientée Objet (POO)
author:
- Rayane ANNEN
- Joris DÉCOPPET
- Hugo DUCOMMUN
- Alexis MARTINS
date: \today{}
geometry: margin=2cm
numbersections: true
header-includes: |
 \usepackage[french]{babel}
 \usepackage{setspace}
 \usepackage{pifont}
 \doublespacing
 \usepackage{float}
 \floatplacement{figure}{H}
 \usepackage{fancyhdr}
 \pagestyle{fancy}
 \fancyhead[LO,LE]{Échecs}
 \fancyfoot[CO,CE]{Programmation orientée objet}
 \fancyfoot[LE,RO]{\thepage}
 \newcommand{\cmark}{\ding{51}}
 \newcommand{\xmark}{\ding{55}}
 \usepackage{rotating}
---

\newpage{}

# Introduction

Ce rapport présente le laboratoire 8 de POO (Programmation Orientée Objet) de la HEIG-VD qui concernait l'implémentation
d'un jeu d'échecs. Principalement, seront présentés la modélisation UML, la conception en Java,
nos hypothèses de travail ainsi que les tests effectués.

# Modélisation UML

\begin{figure}[htbp]
 \centering
 \rotatebox{90}{\includegraphics[width=\textwidth]{../diagramme/diagramme.pdf}}
 \caption{Modélisation UML}
\end{figure}

\newpage{}

# Conception

Cette partie présente comment nous avons conçu notre programme et son but est 
de décrire le rôle et la responsabilité de chacune de nos classes et comment
elles nous ont permis de créer le programme.

## Pièces

Les pièces sont représentées par la classe abstraite `Piece` dans laquelle
on retrouve en particulier le type, la couleur, sa position sur le plateau et la liste des mouvements légaux 
qu'elle peut effectuer.

Chaque pièce est spécialisée selon son type (`King`, `Queen`, `Rook`, `Pawn`, etc.). 
Dans la construction de ces pièces, on initialise la liste des mouvements 
qu'elle peut effectuer.

## Mouvements des pièces

Les mouvements sont représentés par une classe `Move` dans lequel on stocke la pièce qui réalise le mouvement et
si elle peut mettre en échec. Nous avons une méthode permettant de définir si le mouvement peut être effectué.

On spécialise ensuite les mouvements selon les règles du jeu qu'on veut implémenter : en passant, grand et petit roque,
mouvement linéaire, etc.

Pour les mouvements, nous avons utilisé de manière importante le mécanisme de liaison dynamique.
En effet, chaque mouvement implémente la méthode `canMove`, cette méthode est toujours 
appelée dans la classe la plus spécialisée et à chaque fois si le mouvement est possible selon la règle implémentée, 
on appelle le `canMove` de la classe parente, ainsi avec ce mécanisme et l'héritage, on
peut composer différentes règles de déplacement des pièces.

## Plateau du jeu

Le plateau du jeu représente l'état des pièces sur le jeu, on y référence
les deux rois pour une rapidité d'accès pour les vérifications d'échec. 
En plus des rois, on garde une liste des pions, nous permettant de gérer 
plus simplement la promotion de pion.

On trouve aussi des informations concernant les potentielles pièces impliquées dans le dernier mouvement effectué 
(par exemple pour un roque ou une prise en passant), si un mouvement spécial est effectué et finalement 
des méthodes utilitaires nous permettant de modifier l'état du jeu (`put` et `remove` entre autre). 

## Contrôleur

La classe contrôleur va de pair avec celle du plateau de jeu. Le plateau fait la représentation et le contrôleur la logique.

Elle permet de vérifier l'état du jeu en permanence, en s'assurant que les mouvements effectués par les joueurs respectent les règles établies. 
Elle gère également toute la logique du jeu, comme la détection d'échec, mat et pat. En cas de mouvement illégal détecté, le contrôleur peut annuler le coup et informer les joueurs de l'erreur. 

De plus, il peut également gérer les situations spéciales telles que les roques, les prises en passant et les promotions de pions. 
En somme, la classe contrôleur assure un fonctionnement fluide et juste du jeu en vérifiant constamment l'état du jeu et en traitant toute la logique de celui-ci.

# Hypothèses de travail

## Egalité par insuffisance de matériel

L'égalité par insuffisance de matériel est une règle qui varie selon les fédérations et compétitions.
Nous nous sommes donc basés sur sa version la plus simple et la plus répandue. 
Dans cette version, l'égalité est déclarée dans le cas où les deux joueurs n'ont plus qu'un roi et/ou 
un fou et/ou un cavalier sur le plateau de jeu.
Il ne doit rester qu'au maximum un exemplaire de chacune de ces pièces par couleur sur le plateau.

Nous avons utilisé [Wikipédia](https://fr.wikipedia.org/wiki/Roque_(%C3%A9checs)#:~:text=Conditions%20pour%20roquer,-Le%20petit%20roque&text=Le%20roque%20n%C3%A9cessite%20plusieurs%20conditions,avoir%20quitt%C3%A9%20leur%20position%20initiale.)
qui répertorie toutes les règles du jeu pour les cas d'égalité et d'échec.

\newpage{}

# Tests

Les tests réalisés sont des tests fonctionnels et non unitaires.
Chaque test est valide pour les deux joueurs, il n'y a pas de distinction.
Nous les avons regroupés par catégories pour faciliter la lecture.

Les pièces qui n'ont pas de mouvements ou de fonctionnalités particulières ont eu uniquement des tests de déplacements (listés dans pièce).
Le reste des pièces ont des tests spécifiques à leur fonctionnalité dans leur catégorie respective.

## Pièces

Cette partie a été testée pour chaque pièce indépendamment.

| Tests effectués                              | Résultats obtenus |
|:---------------------------------------------|:-----------------:|
| Peut réaliser ses mouvements légaux          |      \cmark       |
| Ne peut pas réaliser des mouvements illégaux |      \cmark       |

### Roi

| Tests effectués                                                           | Résultats obtenus |
|:--------------------------------------------------------------------------|:-----------------:|
| Petit roque (les cases entre la tour et le roi sont libres)               |      \cmark       |
| Grand roque (les cases entre la tour et le roi sont libres)               |      \cmark       |
| Roque impossible en cas d'échec                                           |      \cmark       |
| Roque impossible si une case sur le chemin du roi est attaquée            |      \cmark       |
| Roque impossible si le roi ou la tour a déjà bougé                        |      \cmark       |
| Roque possible si la tour ou la case adjacente (grand roque) est attaquée |      \cmark       |

### Tour

| Tests effectués                                 | Résultats obtenus |
|:------------------------------------------------|:-----------------:|
| Petit roque (réagit bien au petit roque du roi) |      \cmark       |
| Grand roque (réagit bien au grand roque du roi) |      \cmark       |

### Dame

À part ses mouvements légaux, la dame n'a pas de tests à part entière.

### Fou

À part ses mouvements légaux, le fou n'a pas de tests à part entière.

### Pion

| Tests effectués                                                                                 | Résultats obtenus |
|:------------------------------------------------------------------------------------------------|:-----------------:|
| Peut avancer sur une case libre devant lui mais ne peut pas manger                              |      \cmark       |
| Peut avancer de 2 au premier tour si les cases sont vides                                       |      \cmark       |
| Peut prendre un pion en passant                                                                 |      \cmark       |
| Ne peut pas prendre un pion en passant si ce dernier n'a pas bougé de 2 cases au tour précédent |      \cmark       |
| Peut être promu en Reine, Tour, Fou ou Cavalier en atteignant la dernière ligne                 |      \cmark       |

### Cavalier

| Tests effectués                   | Résultats obtenus |
|:----------------------------------|:-----------------:|
| Peut passer par dessus des pièces |      \cmark       |

## Jeu

| Tests effectués                                               | Résultats obtenus |
|:--------------------------------------------------------------|:-----------------:|
| Peut créer une nouvelle partie                                |      \cmark       |
| Début de partie pour les blancs                               |      \cmark       |
| Chaque joueur joue tour par tour en alternance                |      \cmark       |
| Mise en échec détectée                                        |      \cmark       |
| Peut bouger une pièce en échec seulement pour enlever l'échec |      \cmark       |
| Peut manger la pièce qui met en échec pour enlever l'échec    |      \cmark       |
| Ne peut pas enlever une pièce pour rendre son roi vulnérable  |      \cmark       |
| Début de partie pour les blancs                               |      \cmark       |
| L'égalité par PAT fonctionne (stalemate)                      |      \cmark       |
| L'égalité par insuffisance de matériels fonctionne            |      \cmark       |
| La promotion peut échec et mat ou PAT                         |      \cmark       |
| Le roque peut échec et mat ou PAT                             |      \cmark       |

