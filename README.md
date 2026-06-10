# Dasi - Partie 2

Jean & Nicolas

## Routes

### /eleves

| Méthode | `todo`        | Paramètres          | Retour                              |
|---------|---------------|---------------------|-------------------------------------|
| GET     | `lister`      | —                   | `[{ id, nom, prenom, ... }, ...]`   |
| GET     | `recuperer`   | `id`                | `{ id, nom, prenom, ... }`          |
| POST    | `inscrire`    | `nom`, `prenom`, `login`, `pwd` | `{ succes: true }`     |
| POST    | `authentifier`| `login`, `pwd`      | `{ id, nom, prenom, ... }` — stocke l'id en session |

### /intervenants

| Méthode | `todo`        | Paramètres   | Retour                                                      |
|---------|---------------|--------------|-------------------------------------------------------------|
| GET     | `lister`      | —            | `[{ id, nom, prenom, mail, telephone, niveauMin, niveauMax }, ...]` |
| GET     | `recuperer`   | `id`         | `{ id, nom, prenom, mail, telephone, niveauMin, niveauMax }` |
| POST    | `authentifier`| `mail`, `mdp`| `{ succes: true/false }` — stocke l'id en session           |

### /matieres

| Méthode | `todo`                   | Paramètres  | Retour                                          |
|---------|--------------------------|-------------|-------------------------------------------------|
| GET     | `listerMatieres`         | —           | `[{ id, nom }, ...]`                            |
| GET     | `recupererMatiere`       | `id`        | `{ id, nom }`                                   |
| GET     | `listerThemes`           | —           | `[{ id, nom, matiere: { id, nom } }, ...]`      |
| GET     | `recupererTheme`         | `id`        | `{ id, nom, matiere: { id, nom } }`             |
| GET     | `recupererThemesParMatiere` | `idMatiere` | `[{ id, nom, matiere: { id, nom } }, ...]`  |

### /seances

| Méthode | `todo`                 | Paramètres                          | Retour                   | Auth requise      |
|---------|------------------------|-------------------------------------|--------------------------|-------------------|
| GET     | `recuperer`            | `id`                                | SeanceSoutien            | —                 |
| GET     | `recupererAttribuee`   | —                                   | SeanceSoutien            | Intervenant 🔒    |
| GET     | `historiqueEleve`      | —                                   | [SeanceSoutien, ...]     | Élève 🔒          |
| GET     | `historiqueIntervenant`| —                                   | [SeanceSoutien, ...]     | Intervenant 🔒    |
| POST    | `demander`             | `idTheme`, `description`            | SeanceSoutien créée      | Élève 🔒          |
| POST    | `accepter`             | `id`                                | `{ succes: true/false }` | Intervenant 🔒    |
| POST    | `refuser`              | `id`                                | `{ succes: true/false }` | Intervenant 🔒    |
| POST    | `terminer`             | `id`                                | `{ succes: true/false }` | Intervenant 🔒    |
| POST    | `bilan`                | `id`, `compteRendu`                 | `{ succes: true/false }` | Intervenant 🔒    |

### /statistiques

| Méthode | `todo`        | Paramètres | Retour                                                       | Auth requise   |
|---------|---------------|------------|--------------------------------------------------------------|----------------|
| GET     | `intervenant` | —          | `{ entretiensNb, moyenneDuree, colleges: [{id, nom}, ...] }`| Intervenant 🔒 |

---

## Session

L'authentification (`/eleves/authentifier`, `/intervenants/authentifier`) stocke l'id de l'utilisateur en **session HTTP**.

- Les routes marquées 🔒 **nécessitent** une session active du bon type.
- Si la session est déjà ouverte lors d'un appel à `authentifier`, la reconnexion est court-circuitée.
- Les routes 🔒 sans session active retournent une **erreur 401**.
- Les ids (`eleveId`, `intervenantId`) sont automatiquement propagés depuis la session vers la requête — **les paramètres `idEleve` / `idIntervenant` sont ignorés pour ces routes**, l'identité vient toujours de la session.

---

## Codes HTTP retournés

| Code | Situation                                              |
|------|--------------------------------------------------------|
| 200  | Succès                                                 |
| 401  | Route protégée appelée sans session active             |
| 404  | Route inconnue                                         |

---

## Lancer le projet

```bash
./mvnw -DskipTests exec:java
```