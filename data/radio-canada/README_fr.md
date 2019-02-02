##Jeu de données du système de recommandation de Radio-Canada

Ce jeu de données contient les sessions d'usagers recueillies lors des visites sur le site de Radio-Canada.

Chaque rangée représente un événement de lecture d'un article par un usager,
identifié par l'ID de l'événement et la position dans la séquence de lecture de l'usager.

##Colonnes

###SessionId

ID anonymisé de l'usager, ex: `user-0000001`, `user-242718`

###contentId

ID interne identifiant de façon unique le contenu de Radio-Canada. Ces données n'ont pas été anonymisées.
Il est possible d'obtenir le contenu des ces articles en suivant ce format d'url:


https://services.radio-canada.ca/hackathon/neuro/v1/news-stories/CONTENT_ID?client_key=31b2bb0e-85ec-4406-9b22-31c93d7e75f9

Ex:
https://services.radio-canada.ca/hackathon/neuro/v1/news-stories/1149671?client_key=31b2bb0e-85ec-4406-9b22-31c93d7e75f9


###eventId

Id débutant à 0 et identifiant la position du contenu dans la séquence de lecture de l'usager.
Par exemple, un eventId de `2` indique que c'est le 3e article lu par l'usager.

##Fichier

###train.csv

Ceci est le fichier principal pour l'entraînement du modèle, 
contenant tous les événements de lecture des usagers sur la période de temps choisie.

###test.csv

Ceci est le fichier servant à évaluer la perfomance du modèle. Le format de ce fichier est le même que celui d'entraînement.


Notes:

- Les événements de test ont été pris sur une période de temps **après** la période d'entraînement -- il n'y a pas de chevauchement dans les dates.

- Plusieurs usagers dans le fichier de test sont également dans le fichier d'entraînement, mais certains n'y sont pas.

- Certain contenus dans le jeu de données ont été publiés après la fin de la période d'entraînement, ainsi, 
ils n'apparaîssent pas dans le fichier d'entraînement.

