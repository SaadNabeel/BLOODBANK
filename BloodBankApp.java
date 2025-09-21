
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