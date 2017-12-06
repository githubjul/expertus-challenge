#Julien's expertus challenge

##Le défi Expertus

Le défi était ouvert, puisque la seule contrainte était de présenter une page "Hello Expertus"

Une fois cette page réalisée, j'ai eu envie de faire un petit jeu. Je vous laisse le découvrir, en espérant que cela vous conviendra.

[Accès à la démo](https://julien-expertus-challenge.herokuapp.com)

Nb : Au premier lancement, il est normal d'avoir un temps d'attente de quelques secondes, car l'instance du serveur s'arrête automatiquement en cas d'inactivité. Il faut donc qu'elle redémarre.


##Côté technique

La demande était d'avoir un back-end en Java.

Aucune contrainte n'était précisée pour la parrtie front-end

###Back-end : Spring Boot

Mon back-end est basé sur Spring Boot, l'architecture présentée est telle que j'ai l'habitude de le faire.

S'agissant d'un test, pour des raisons de simplicité d'exécution de votre côté et de déploiement du mien, les données nécessaires sont stockées sous forme de ressources json, et pas en base de données.

Quelques détails :
* Utilisation de streams : filter sur List
* utilisation de la réflexivité : appel dynamique de méthodes sur des objets
* i18n : fonctionne en Anglais et Français
* Utilisation de Lombok : moins de code inutile à écrire [Infos sur Lombok](https://projectlombok.org/)
* RestController : transmission d'infos au front-end via des flux Json

###Frond-end : jQuery/Bootstrap

La partie front-end se veut relativement simple. Je ne suis pas un expert en Angular/ReactJs, donc je suis resté sur des technologies que je maitrise.

Quelques détails :
* Javascript : fonctionnement avec des espaces de noms (packages) pour éviter tout risque de collisions dans le nommage des méthodes
* Sass : utilisation de sass, même si pour cet exemple je n'en tire pas réellement partie
* Grunt : mise en place de process de minification/merge de js/scss/css afin d'optimiser la gestion des ressources statiques
* Responsive : fonctionne aussi bien sur mobile que sur desktop


###CICD

Dans une logique d'organisation "DevOps" le projet est bâti dans un environnement type CiCd

La plateforme cloud utilisée pour cela est Heroku.

Il y a donc un déploiement continu sur Heroku dès que je fais un push d'une version stable sur mon remote Heroku

Le projet est testable sur cette plateforme :

https://julien-expertus-challenge.herokuapp.com


### + d'infos

N'hésitez pas à me contacter si vous souhaitez d'autres informations sur le projet 

 




