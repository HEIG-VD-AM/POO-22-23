# Laboratoire 3. UML

_Durée du laboratoire: 2 périodes. A rendre le vendredi 21 octobre 2022, au début de la séance de laboratoire._

## Agence de voyage

Définir le diagramme de classes UML permettant de représenter le fonctionnement d’un groupe d’agences de
voyage.
- Une agence de voyage est caractérisée par son nom, son adresse (rue, numéro, ville, NPA), et ses employés.
- Chaque employé est caractérisé par son nom, son prénom, son salaire mensuel, son numéro de téléphone, sa date de naissance, son adresse et l'agence dans laquelle il travaille. On supposera ici qu’un employé ne change jamais d’agence.
- Un voyage est caractérisé par son numéro de réservation, sa date de réservation, sa date de début, sa date de fin et ses participants. Il comporte une ou plusieurs étapes. Un voyage ne peut impliquer plus de 10 personnes.
- Une étape est caractérisée par la ville où elle a lieu et les date d'arrivée et de départ des participants.
- A chaque étape, un hébergement unique, caractérisé par son nom, son prix par nuitée et son adresse, est réservé pour l'ensemble des participants. Il peut s'agir d’un hôtel (de 1 à 5 étoiles), d’un bed and breakfast ou d’un camping (avec ou sans piscine). Tous les établissements offrent un rabais de 30% pour les enfants de moins de 12 ans.
- Un participant à un voyage est caractérisé par son nom, son prénom, son numéro de téléphone, son adresse et sa date de naissance.
- Un client qui désire réserver un voyage se rend dans une agence. Un employé de celle-ci organisera alors le voyage en relevant les données de chaque participant et en établissant les différentes étapes du voyage.

Le système doit permettre d’effectuer les opérations suivantes (parmis d’autres):
- Lister pour une ville donnée l’ensemble de ses hôtels 3 étoiles.
- Calculer le nombre de voyages que chaque employé a organisé.
- Lister les voyages réservés par un participant donné dans une agence donnée.

### Remarques
- Documenter vos choix et hypothèses de travail.
- Attention à bien factoriser les informations redondantes.
- Indiquer le type des attributs, le type de retour des méthodes ainsi que les types de leurs paramètres.
- Ne pas indiquer les constructeurs ni les accesseurs triviaux.
- Indiquer toutes les cardinalités des associations ainsi que leur nom (ou leurs rôles).
- Indiquer les signatures des méthodes nécessaires pour répondre aux traitements de la donnée.
- Spécifier en français les contraintes d’intégrité qui ne peuvent pas être exprimées sur le diagramme.
- Il n'est pas nécessaire d'indiquer le sens de navigation des associations

### Rendu
Le diagramme de classes ainsi que les contraintes d’intégrité éventuelles sont à rendre sous format papier, bien
présentés.
