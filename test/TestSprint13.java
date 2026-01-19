import java.util.HashMap;

public class TestSprint13 {
    public static class Emp {
        @AnnotAttribut(name = "anarana") String nom;
        @AnnotAttribut(name = "taona") int age;

        public String getNom() { return nom; }
        public void setNom(String nom) { this.nom = nom; }
        public int getAge() { return age; }
        public void setAge(int age) { this.age = age; }
        public String toString() { return nom + ", " + age + " ans"; }
    }

    public static void main(String[] args) throws Exception {
        // Simule des champs de formulaire nommes "emp.anarana" et "emp.taona"
        HashMap<String, String> requeteSimulee = new HashMap<>();
        requeteSimulee.put("emp.anarana", "Diary");
        requeteSimulee.put("emp.taona", "20");

        Emp emp = (Emp) ObjectBinder.construireDepuisParametres(Emp.class, "emp", requeteSimulee);
        System.out.println("Objet reconstitue : " + emp);
    }
}
