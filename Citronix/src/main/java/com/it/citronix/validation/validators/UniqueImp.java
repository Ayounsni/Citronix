package com.it.citronix.validation.validators;

import com.it.citronix.validation.annotations.Unique;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class UniqueImp implements ConstraintValidator<Unique, String> {

    @PersistenceContext
    private EntityManager manager;

    private Class<?> entityClass;
    private String field;

    private static final String ID_FIELD_NAME = "id"; // Nom de l'ID par convention

    @Override
    public void initialize(Unique constraintAnnotation) {
        this.entityClass = constraintAnnotation.entity();
        this.field = constraintAnnotation.field();
    }

    public boolean isValidWithExclusion(String value, Long excludeId) {
        if (value == null || field == null) {
            return true; // Champ ou valeur null, pas besoin de valider
        }

        String jpql = "SELECT COUNT(e) FROM " + entityClass.getSimpleName() + " e WHERE LOWER(e." + field + ") = LOWER(:value)";
        jpql += " AND (e.id != :excludeId OR :excludeId IS NULL)";

        TypedQuery<Long> query = manager.createQuery(jpql, Long.class);
        query.setParameter("value", value);
        query.setParameter("excludeId", excludeId);

        Long count = query.getSingleResult();
        return count == 0;
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // Appeler ici isValidWithExclusion avec null si aucun ID n'est à exclure
        return isValidWithExclusion(value, null);
    }

}
