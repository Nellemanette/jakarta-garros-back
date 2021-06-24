# Jakarta Garros Back

## Architecture :

- Le dossier `src` contient tout le code source, c'est là qu'on ajouteras notre code, dans le dossier `main` :

- Dans le dossier `java` :

  - Le package `constantes`:
  
  Il contient les constantes utilisées 
  
  - Le package `controleurs` :
     
  Il contient l'ensemble de nos Servlets.
  Il s'agit des contrôleurs, et ici il s'agit d'une API REST, voir l'exemple SousTournoiController.
  Pour chaque nouvelle URL, il faudra une nouvelle servlet, même si cela traîte de la même chose.
   
  - Le package `convertisseurs` :
  
  Contient les classes permettant de convertir une entité (package `entites` du package `donnees`) en DTO afin de les renvoyer depuis les services vers les contrôleurs.
  De cette façon, il n'y aura aucune entité dans les contrôleurs.
  L'objet retourné par chacune de nos requêtes sera du type **Json**.
  (voir avertissement du package `donnees`)
  
  - Le package `donnees` : 
  
  Restriction au niveau du package donnees.
  
  **Le package `entites` va contenir toutes les entités (équivalent du Modele en C#), les objets mappés vers la BDD.**
  
  **Le package `dto` va contenir tout les DTOs, les objets qui seront convertis en JSON, et retournés par l'API.**
  
  **Il n'y aura aucune entité dans un contrôleur !**
  
  Ce qui circule entre contrôleur et service, c'est un Dto uniquement !
  Par contre dans un Service (package `services`) et dans les repertoires (repositories), on pourra avoir des entités.
  Pas d'objet liés à la BDD dans le contrôleur, cela créera beaucoup de conflits.
  
  - Le package `repertoires` :
  
  Couche d'accès et de modifications à la BDD.
  Modifs en BDD et accès. (Add, Remove, Update...)
  
  - Le package `services`:
    
  Il contient entre autres un service permettant de renvoyer une réponse parametrée au front (IGestionnaireReponseService).
  Ce package contient les services au sens logique-métier. Pour chaque Service, il faut une interface.
  (Si on veut gérer des courts, on ajouteras sûrement le package court avec sa classe CourtService, et son interface).
  
  - Le dossier `resources`:
  
  Dans le sous-dossier `META-INF`, le fichier `beans.xml` est issu de la librairie externe de la validation des beans. C'est toute la configuration de la validation des javabeans (Classes DTO, DAO etc...).
  
  Dans le sous-dossier `META-INF`, le fichier `persistence.xml` est le fichier qui va permettre de configurer l'accès à la BDD.
  
  - Le dossier `webapp` :
  
  Dans le sous-dossier `WEB-INF`, le fichier `jboss-web.xml` est nécessaire pour configurer le serveur WildFly.
  
  Dans le sous-dossier `WEB-INF`, le fichier `web.xml` est le fichier de configuration des servlets et des serveurs d'application.
  
  - Le dossier `test` :
  
  Pour les tests unitaires ou d'intégration.
  
  - Le dossier `target`:
  
  Il est auto-généré et fait partie du .gitignore.
  
  - Le fichier `.gitignore`:
  
  Il a la même fonctionnalité qu'en front. Il va permettre de ne pas push certains fichiers lors d'un commit.
  Ici, on ignore le target, le .iml etc...
  
  - Le fichier `pom.xml`:
  
  Il est important, il contient toutes les dépendances et les configuration de maven qui sont utilisées dans notre projet.
  