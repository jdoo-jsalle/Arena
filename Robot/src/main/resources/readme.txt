# Projet Robot - README

## Présentation

Ce projet simule une arène de robots programmables. Chaque robot exécute un programme défini dans un fichier, interagit avec l’arène et les autres robots selon des règles configurables.

---

## Structure du projet

- **src/main/java/** : Code source principal (moteur, interface utilisateur, logique robot, parsing…)
- **src/test/java/** : Tests unitaires et classes de lancement pour le développement
- **src/exemple/** : Exemples de fichiers de configuration d’arène et de programmes robots
- **src/main/resources/** : Ressources (logs, ce fichier README, etc.)

---

## Lancer le projet

### Depuis l’IDE (Eclipse, IntelliJ…)

- Lancer la classe de test principale :
  ```
  com.js.dawa.TestMainRobot
  ```
  (située dans `src/test/java`)

### Depuis la ligne de commande

- Compiler le projet avec Maven :
  ```
  mvn clean package
  ```
- Lancer l’application :
  ```
  java -classpath target/Robot-1.0.0.jar;target/lib/* com.js.dawa.MainRobot -D chemin/vers/exemple
  ```
  - `-D` : dossier contenant le fichier `Arene.properties`

Exemple (Windows, depuis le dossier du jar) :
```
java -classpath %~dp0Robot-1.0.0.jar;%~dp0lib\* com.js.dawa.MainRobot -D %~dp0exemple
```

---

## Configuration de l’arène

- Les fichiers de configuration se trouvent dans `src/exemple/`
- **Arene.properties** : décrit l’arène, les robots présents, leurs programmes et positions initiales.
- **Robot_*.robot** : fichiers de programme pour chaque robot.

Exemple de structure de dossier d’exemple :
```
src/exemple/
   Arene.properties : description de l’arène avec robots, programmes robots, et leur position.
   Robot_Berserk_one.robot
   Robot_detector.robot
   ...
```

---

## Développement & Tests

- Le projet utilise Maven pour la gestion des dépendances.
- Les logs sont configurés via `src/main/resources/logback.xml`.
- Les tests sont dans `src/test/java` et peuvent être lancés avec :
  ```
  mvn test
  ```

---

## Historique

- 15/05/2025 : Première version du projet.