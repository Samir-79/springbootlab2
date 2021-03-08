package se.iths.springbootlab2.restquery;

//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.core.types.dsl.NumberPath;
//import com.querydsl.core.types.dsl.PathBuilder;
//import com.querydsl.core.types.dsl.StringPath;

import se.iths.springbootlab2.entities.Books;

public class BooksPredicate {


  /*  private SearchCriteria criteria;

    public BooksPredicate(SearchCriteria criteria) {
        this.criteria = criteria;
    }

    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
            double i = Integer.parseInt(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }


    public BooleanExpression getPredicate() {
        PathBuilder<Books> entityPath = new PathBuilder<>(Books.class, "books");

        if (isNumeric(criteria.getValue().toString())) {
            NumberPath<Integer> path = entityPath.getNumber(criteria.getKey(), Integer.class);
            int value = Integer.parseInt(criteria.getValue().toString());
            switch (criteria.getOperation()) {
                case ":":
                    return path.eq(value);
                case ">":
                    return path.goe(value);
                case "<":
                    return path.loe(value);
            }
        }
        else {
            StringPath path = entityPath.getString(criteria.getKey());
            if (criteria.getOperation().equalsIgnoreCase(":")) {
                return path.containsIgnoreCase(criteria.getValue().toString());
            }
        }
        return null;
    }*/
}
