
public class BloodBankApp {

    // ==== Models ====

    static class Donor {
        int id;
        String name, bloodGroup, phone;
        int age;

        Donor(int id, String name, String bg, int age, String phone) {
            this.id = id;
            this.name = name;
            this.bloodGroup = bg;
            this.age = age;
            this.phone = phone;
        }

        public String toString() {
            return id + " | " + name + " | " + bloodGroup + " | " + age + " | " + phone;
        }
    }

    static class BloodUnit {
        int id;
        String bloodGroup;
        int quantity;

        BloodUnit(int id, String bg, int q) {
            this.id = id;
            this.bloodGroup = bg;
            this.quantity = q;
        }

        public String toString() {
            return id + " | " + bloodGroup + " | " + quantity;
        }
    }

    static class Request {
        int id;
        String name, bloodGroup, status;
        int units;

        Request(int id, String name, String bg, int units, String status) {
            this.id = id;
            this.name = name;
            this.bloodGroup = bg;
            this.units = units;
            this.status = status;
        }

        public String toString() {
            return id + " | " + name + " | " + bloodGroup + " | " + units + " | " + status;
        }
    }
    import java.io.*;
import java.util.*;

public class BloodBankApp {

    // ==== Models ====

    static class Donor {
        int id;
        String name, bloodGroup, phone;
        int age;

        Donor(int id, String name, String bg, int age, String phone) {
            this.id = id;
            this.name = name;
            this.bloodGroup = bg;
            this.age = age;
            this.phone = phone;
        }

        public String toString() {
            return id + " | " + name + " | " + bloodGroup + " | " + age + " | " + phone;
        }
    }

    static class BloodUnit {
        int id;
        String bloodGroup;
        int quantity;

        BloodUnit(int id, String bg, int q) {
            this.id = id;
            this.bloodGroup = bg;
            this.quantity = q;
        }

        public String toString() {
            return id + " | " + bloodGroup + " | " + quantity;
        }
    }

    static class Request {
        int id;
        String name, bloodGroup, status;
        int units;

        Request(int id, String name, String bg, int units, String status) {
            this.id = id;
            this.name = name;
            this.bloodGroup = bg;
            this.units = units;
            this.status = status;
        }

        public String toString() {
            return id + " | " + name + " | " + bloodGroup + " | " + units + " | " + status;
        }
    }

    // ---- Utility for File I/O ----
    static class FileUtil {

        static List<String[]> readFile(String path) {
            List<String[]> list = new ArrayList<>();
            try (BufferedReader br = new BufferedReader(new FileReader(path))) {
                String line;
                while ((line = br.readLine()) != null) {
                    list.add(line.split("\\|"));
                }
            } catch (IOException ignored) {}
            return list;
        }

        static void writeFile(String path, List<String> lines) {
            try (BufferedWriter bw = new BufferedWriter(new FileWriter(path))) {
                for (String line : lines) {
                    bw.write(line);
                    bw.newLine();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
static class DonorManager {

    private final String FILE = "donors.txt";
    private List<Donor> donors = new ArrayList<>();

    DonorManager() {
        load();
    }

    private void load() {
        donors.clear();
        for (String[] p : FileUtil.readFile(FILE)) {
            if (p.length >= 5) {
                donors.add(new Donor(
                        Integer.parseInt(p[0].trim()),
                        p[1].trim(),
                        p[2].trim(),
                        Integer.parseInt(p[3].trim()),
                        p[4].trim()
                ));
            }
        }
    }

    private void save() {
        List<String> lines = new ArrayList<>();
        for (Donor d : donors) {
            lines.add(d.id + "|" + d.name + "|" + d.bloodGroup + "|" + d.age + "|" + d.phone);
        }
        FileUtil.writeFile(FILE, lines);
    }

    void add(Donor d) {
        donors.add(d);
        save();
    }

    void edit(int id, Donor upd) {
        for (int i = 0; i < donors.size(); i++) {
            if (donors.get(i).id == id) {
                donors.set(i, upd);
                break;
            }
        }
        save();
    }

    void delete(int id) {
        donors.removeIf(d -> d.id == id);
        save();
    }

    List<Donor> getAll() {
        return donors;
    }
}
private void save() {
    List<String> lines = new ArrayList<>();
    for (Donor d : donors) {
        lines.add(d.id + "|" + d.name + "|" + d.bloodGroup + "|" + d.age + "|" + d.phone);
    }
    FileUtil.writeFile(FILE, lines);
}

void add(Donor d) {
    donors.add(d);
    save();
}

void edit(int id, Donor upd) {
    for (int i = 0; i < donors.size(); i++) {
        if (donors.get(i).id == id) {   // fixed comparison
            donors.set(i, upd);
            break;
        }
    }
    save();
}

void delete(int id) {
    donors.removeIf(d -> d.id == id);   // fixed comparison
    save();
}

List<Donor> getAll() {
    return donors;
}
static class BloodUnitManager {

private final String FILE="bloodunits.txt";

private List<BloodUnit> units=new ArrayList<>();

BloodUnitManager() { load(); }

private void load(){

units.clear();

for(String[] p: FileUtil.readFile(FILE)){

if(p.length>=3) units.add(new BloodUnit (Integer.parseInt(p[0].trim()),p[1].trim(),

Integer.parseInt(p[2].trim())));

}

}

private void save(){

List<String> lines=new ArrayList<>();

for(BloodUnit u:units)

lines.add(u.id+" "+u.bloodGroup+"/"+u.quantity);

FileUtil.writeFile(FILE, lines);

}

}

void add(BloodUnit u) { units.add(u); save(); }

void updateQty(int id, int q) { for (BloodUnit u:units) if(u.id=id) { u.quantity=q; } save(); }

List<BloodUnit> getAll() { return units; }
static class RequestManager {

private final String FILE="requests.txt";

private List<Request> requests = new ArrayList<>();

RequestManager() { load(); }

private void load(){

requests.clear();

for(String[] p: FileUtil.readFile(FILE)) {

if(p.length>=5) requests.add(new Request (Integer.parseInt(p[0].trim()),p[1].trim(),

p[2].trim(),Integer.parseInt(p[3].trim()),p[4].trim()));

}

}

}
private void save(){

List<String> lines=new ArrayList<>();

for(Request r:requests)

lines.add(r.id+"|"+r.name+"/"+r.bloodGroup+"/"+r.units+"/"+r.status);

FileUtil.writeFile(FILE, lines);

}

void add(Request r) { requests.add(r); save(); }

void updateStatus(int id, String status) { 
    for (Request r: requests) 
        if(r.id==id){ r.status=status; } 
    save(); 
}

List<Request> getAll() { return requests; }

}
private static DonorManager donorMgr=new DonorManager();
private static BloodUnitManager unitMgr=new BloodUnitManager();
private static RequestManager reqMgr=new RequestManager();
private static Scanner sc=new Scanner(System.in);

public static void main(String[] args) {

    while(true) {
        System.out.println("\n=== BLOOD BANK SYSTEM ===");
        System.out.println("1. Donors\n2. Blood Units\n3. Requests\n4. Exit");
        System.out.print("Choice: ");

        int ch=sc.nextInt(); 
        sc.nextLine();

        switch(ch){
            case 1 -> donorMenu();
            case 2 -> unitMenu();
            case 3 -> requestMenu();
            case 4 -> { 
                System.out.println("Exiting..."); 
                return; 
            }
            default -> System.out.println("Invalid choice");
        }
    }
}
private static void donorMenu() {
    System.out.println("--- Donors ---");
    System.out.println("1. Add\n2. View\n3. Edit\n4. Delete\n5. Back");

    int c = sc.nextInt(); 
    sc.nextLine();

    switch(c) {
        case 1 -> {
            System.out.print("ID: "); 
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Name: "); 
            String n = sc.nextLine();

            System.out.print("Blood Group: "); 
            String bg = sc.nextLine();

            System.out.print("Age: "); 
            int age = sc.nextInt(); sc.nextLine();

            System.out.print("Phone: "); 
            String ph = sc.nextLine();

            donorMgr.add(new Donor(id, n, bg, age, ph));
        }

        case 2 -> donorMgr.getAll().forEach(System.out::println);

        case 3 -> {
            System.out.print("ID to edit: "); 
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("New Name: "); 
            String n = sc.nextLine();

            System.out.print("Blood Group: "); 
            String bg = sc.nextLine();

            System.out.print("Age: "); 
            int age = sc.nextInt(); sc.nextLine();

            System.out.print("Phone: "); 
            String ph = sc.nextLine();

            donorMgr.edit(id, new Donor(id, n, bg, age, ph));
        }

        case 4 -> {
            System.out.print("ID to delete: "); 
            int id = sc.nextInt(); 
            donorMgr.delete(id); 
        }
    }
}
private static void unitMenu() {
    System.out.println("--- Blood Units ---");
    System.out.println("1. Add\n2. View\n3. Update Quantity\n4. Back");

    int c = sc.nextInt(); 
    sc.nextLine();

    switch(c) {
        case 1 -> {
            System.out.print("ID: "); 
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Blood Group: "); 
            String bg = sc.nextLine();

            System.out.print("Quantity: "); 
            int q = sc.nextInt(); sc.nextLine();

            unitMgr.add(new BloodUnit(id, bg, q));
        }

        case 2 -> unitMgr.getAll().forEach(System.out::println);

        case 3 -> {
            System.out.print("ID: "); 
            int id = sc.nextInt();

            System.out.print("New Quantity: "); 
            int q = sc.nextInt();

            unitMgr.updateQty(id, q);
        }
    }
}
private static void requestMenu() {
    System.out.println("\n--- Requests ---");
    System.out.println("1. Add Request\n2. View\n3. Update Status\n4. Back");

    int c = sc.nextInt(); 
    sc.nextLine();

    switch(c) {
        case 1 -> {
            System.out.print("ID: "); 
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("Patient Name: "); 
            String n = sc.nextLine();

            System.out.print("Blood Group: "); 
            String bg = sc.nextLine();

            System.out.print("Units: "); 
            int u = sc.nextInt(); sc.nextLine();

            reqMgr.add(new Request(id, n, bg, u, "Pending"));
        }

        case 2 -> reqMgr.getAll().forEach(System.out::println);

        case 3 -> {
            System.out.print("ID: "); 
            int id = sc.nextInt(); sc.nextLine();

            System.out.print("New Status: "); 
            String s = sc.nextLine();

            reqMgr.updateStatus(id, s);
        }
    }
}
