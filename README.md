# Dasi - Partie 2

Jean & Nicolas

## Routes

### /eleves

| Méthode | `todo`        | Paramètres          | Retour                              |
|---------|---------------|---------------------|-------------------------------------|
| GET     | `lister`      | —                   | `[{ id, nom, prenom, ... }, ...]`   |
| GET     | `recuperer`   | `id`                | `{ id, nom, prenom, ... }`          |
| POST    | `inscrire`    | `nom`, `prenom`, `login`, `pwd` | `{ succes: true }`     |
| POST    | `authentifier`| `login`, `pwd`      | `{ succes: true }`                  |

### /intervenants

| Méthode | `todo`        | Paramètres   | Retour                                                      |
|---------|---------------|--------------|-------------------------------------------------------------|
| GET     | `lister`      | —            | `[{ id, nom, prenom, mail, telephone, niveauMin, niveauMax }, ...]` |
| GET     | `recuperer`   | `id`         | `{ id, nom, prenom, mail, telephone, niveauMin, niveauMax }` |
| POST    | `authentifier`| `mail`, `mdp`| `{ succes: true/false }`                                    |

### /matieres

| Méthode | `todo`                   | Paramètres | Retour                                          |
|---------|--------------------------|------------|-------------------------------------------------|
| GET     | `listerMatieres`         | —          | `[{ id, nom }, ...]`                            |
| GET     | `recupererMatiere`       | `id`       | `{ id, nom }`                                   |
| GET     | `listerThemes`           | —          | `[{ id, nom, matiere: { id, nom } }, ...]`      |
| GET     | `recupererTheme`         | `id`       | `{ id, nom, matiere: { id, nom } }`             |
| GET     | `recupererThemesParMatiere` | `idMatiere` | `[{ id, nom, matiere: { id, nom } }, ...]`   |



## Lancer un projet maven

./mvnw -DskipTests exec:java
