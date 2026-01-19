import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Sprint 13 : reconstruire un OBJET complet a partir de parametres "poin-notes"
 * type "emp.nom", "emp.age" (comme dans Outil.checkParamClass du vrai framework,
 * annote la-bas : "// Sprint 13 , Sprint 14"). Ici req.getParameterNames() est
 * simule par une HashMap<String,String>.
 */
public class ObjectBinder {
    public static Object construireDepuisParametres(Class<?> classeCible, String prefixe, HashMap<String, String> requeteSimulee) throws Exception {
        Object instance = classeCible.getDeclaredConstructor().newInstance();
        Field[] champs = classeCible.getDeclaredFields();

        for (Field champ : champs) {
            String nomParam = champ.getName();
            if (champ.getAnnotation(AnnotAttribut.class) != null) {
                nomParam = champ.getAnnotation(AnnotAttribut.class).name();
            }
            String cleComplete = prefixe + "." + nomParam;
            String valeur = requeteSimulee.get(cleComplete);
            if (valeur == null) continue;

            String nomSetter = "set" + Character.toUpperCase(champ.getName().charAt(0)) + champ.getName().substring(1);
            Method setter = classeCible.getDeclaredMethod(nomSetter, champ.getType());

            if (champ.getType() == int.class) {
                setter.invoke(instance, Integer.parseInt(valeur));
            } else {
                setter.invoke(instance, valeur);
            }
        }
        return instance;
    }
}
