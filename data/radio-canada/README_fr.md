##Jeu de donn�es du syst�me de recommandation de Radio-Canada

Ce jeu de donn�es contient les sessions d'usagers recueillies lors des visites sur le site de Radio-Canada.

Chaque rang�e repr�sente un �v�nement de lecture d'un article par un usager,
identifi� par l'ID de l'�v�nement et la position dans la s�quence de lecture de l'usager.

##Colonnes

###SessionId

ID anonymis� de l'usager, ex: `user-0000001`, `user-242718`

###contentId

ID interne identifiant de fa�on unique le contenu de Radio-Canada. Ces donn�es n'ont pas �t� anonymis�es.
Il est possible d'obtenir le contenu des ces articles en suivant ce format d'url:


https://services.radio-canada.ca/hackathon/neuro/v1/news-stories/CONTENT_ID?client_key=31b2bb0e-85ec-4406-9b22-31c93d7e75f9

Ex:
https://services.radio-canada.ca/hackathon/neuro/v1/news-stories/1149671?client_key=31b2bb0e-85ec-4406-9b22-31c93d7e75f9


###eventId

Id d�butant � 0 et identifiant la position du contenu dans la s�quence de lecture de l'usager.
Par exemple, un eventId de `2` indique que c'est le 3e article lu par l'usager.

##Fichier

###train.csv

Ceci est le fichier principal pour l'entra�nement du mod�le, 
contenant tous les �v�nements de lecture des usagers sur la p�riode de temps choisie.

###test.csv

Ceci est le fichier servant � �valuer la perfomance du mod�le. Le format de ce fichier est le m�me que celui d'entra�nement.


Notes:

- Les �v�nements de test ont �t� pris sur une p�riode de temps **apr�s** la p�riode d'entra�nement -- il n'y a pas de chevauchement dans les dates.

- Plusieurs usagers dans le fichier de test sont �galement dans le fichier d'entra�nement, mais certains n'y sont pas.

- Certain contenus dans le jeu de donn�es ont �t� publi�s apr�s la fin de la p�riode d'entra�nement, ainsi, 
ils n'appara�ssent pas dans le fichier d'entra�nement.

