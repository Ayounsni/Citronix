# Citronix 🍋

## **Description du Projet**
**Citronix** est une application de gestion conçue pour les fermes de citrons. Elle permet aux agriculteurs de suivre la production, la récolte et la vente de leurs produits, tout en optimisant la gestion des fermes, des champs, des arbres et de leur productivité.

---

## **Fonctionnalités Principales**
### **1. Gestion des Fermes**
- Créer, modifier et consulter les informations d'une ferme (nom, localisation, superficie, date de création).
- Recherche multicritère (Criteria Builder).

### **2. Gestion des Champs**
- Associer des champs à une ferme avec une superficie définie.
- Assurer la cohérence des superficies :  
  - La somme des superficies des champs d'une ferme doit être strictement inférieure à celle de la ferme.

### **3. Gestion des Arbres**
- Suivre les arbres avec leur date de plantation, âge, et champ d'appartenance.
- Calcul automatique de l'âge des arbres.
- Détermination de la productivité annuelle des arbres :  
  - Arbre jeune (< 3 ans) : 2,5 kg / saison.  
  - Arbre mature (3-10 ans) : 12 kg / saison.  
  - Arbre vieux (> 10 ans) : 20 kg / saison.  

### **4. Gestion des Récoltes**
- Suivre les récoltes par saison (hiver, printemps, été, automne).
- Une seule récolte par saison (tous les 3 mois).
- Enregistrer la date de récolte et la quantité totale récoltée.

### **5. Détail des Récoltes**
- Suivre la quantité récoltée par arbre pour une récolte donnée.
- Associer chaque détail de récolte à un arbre spécifique.

### **6. Gestion des Ventes**
- Enregistrer les ventes avec la date, le prix unitaire, le client, et la récolte associée.
- Calcul automatique du revenu :  
  **Revenu = quantité * prixUnitaire.**

---

## **Contraintes**
1. **Superficie minimale des champs** : 0,1 hectare (1 000 m²).  
2. **Superficie maximale des champs** : Aucun champ ne peut dépasser 50% de la superficie totale de la ferme.  
3. **Nombre maximal de champs par ferme** : 10 champs.  
4. **Espacement entre les arbres** :  
   - Densité maximale de 100 arbres par hectare (10 arbres par 1 000 m²).  
5. **Durée de vie maximale des arbres** : 20 ans (au-delà, les arbres sont non productifs).  
6. **Période de plantation** : Mars à Mai uniquement.  
7. **Limite saisonnière** : Un champ ne peut être associé qu'à une seule récolte par saison.  
8. **Arbres non récoltés deux fois** : Un arbre ne peut être inclus dans plus d’une récolte pour une même saison.  

---

## **Exigences Techniques**
- **Framework** : Spring Boot pour l'API REST.  
- **Architecture** : Architecture en couches (Controller, Service, Repository, Entity).  
- **Validation** : Validation des données avec les annotations Spring.  
- **Programmation** : Utilisation des interfaces et implémentation.  
- **Gestion des Exceptions** : Gestion centralisée des erreurs.  
- **Tests** : Tests unitaires avec **JUnit** et **Mockito**.  
- **Simplification des Entités** : Lombok et Builder Pattern.  
- **Conversion des Données** : MapStruct pour les conversions entre entités, DTO et View Models.  

---

## **Installation et Lancement**
1. Clonez ce dépôt :  
   ```bash
   git clone https://github.com/Ayounsni/Citronix