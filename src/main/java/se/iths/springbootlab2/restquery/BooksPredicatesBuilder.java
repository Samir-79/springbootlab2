package se.iths.springbootlab2.restquery;

//import com.querydsl.core.types.dsl.BooleanExpression;
//import com.querydsl.core.types.dsl.Expressions;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class BooksPredicatesBuilder {

    /*private List<SearchCriteria> params;



    public BooksPredicatesBuilder() {
        params = new ArrayList<>();
    }

    public BooksPredicatesBuilder with(
            String key, String operation, Object value) {

        params.add(new SearchCriteria(key, operation, value));
        return this;
    }

    public BooleanExpression build() {
        if (params.size() == 0) {
            return null;
        }

        List predicates = params.stream().map(param -> {
            BooksPredicate predicate = new BooksPredicate(param);
            return predicate.getPredicate();
        }).filter(Objects::nonNull).collect(Collectors.toList());

        BooleanExpression result = Expressions.asBoolean(true).isTrue();
        for (BooleanExpression predicate : predicates) {
            result = result.and(predicate);
        }
        return result;
    }*/


}
