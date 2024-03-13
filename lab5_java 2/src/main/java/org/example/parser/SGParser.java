package org.example.parser;
import org.example.interfaces.Printer;
import org.example.models.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import org.example.managers.CollectionManager;


public class SGParser extends DefaultTypeParser {

    public SGParser(Scanner scanner, Printer printer) {
        super(scanner, printer);
    }

    public Person parsePerson() {
        print("PERSON:");
        // Person Name
        String name;
        while (!Person.validateName(name = parseString("Name", "not null"))) print("Invalid Name.");
        String passportID;

        ArrayList<String> pIds= new ArrayList<String>();
        for(StudyGroup n : CollectionManager.getCollection()){
            pIds.add(n.getGroupAdmin().getName());
        }

        while (!Person.validatePID(passportID = parseString("passportID", "not null,")) || pIds.contains(passportID)) print("Invalid passportID");
        //Person hairColor
        Color hairColor;
        String colorValues = Arrays.asList(Color.values()).toString();
        while (!Person.validateColor(hairColor = parseEnum(Color.class, "Color " + colorValues, "not null"))) {
            print("Invalid Color " + colorValues);
        }


        //Person location
        Location location = parseLocation();
                return new Person(name, passportID,hairColor,location );
    }
    public Location parseLocation() {
        print("LOCATION:");
        // Location X
        Integer x;
        while (!Location.validateX(x = parseInteger("X", "not null"))) print("Invalid X.");
        // Location Y
        Integer y;
        while (!Location.validateY(y = parseInteger("Y", "not null,Integer"))) print("Invalid Y.");
        //Location name
        String name;
        while (!Location.validateName(name = parseString("Name", "not null"))) print("Invalid Name.");
        return new Location(x, y, name);
    }

    public Coordinates parseCoordinates() {
        print("COORDINATES:");
        // Coordinate X
        int x;
        while (!Coordinates.validateX(x = parseInt("X", "not null, long, min -951"))) print("Invalid X.");
        // Coordinate Y
        long y;
        while (!Coordinates.validateY(y = parseLong("Y", "not null, float, max 779"))) print("Invalid Y.");
        return new Coordinates(x, y);
    }

    public StudyGroup parseStudyGroup() {
        print("STUDY_GROUP:");
        // Flat Name
        String name;
        while (!StudyGroup.validateName(name = parseString("Name", "not null, not empty"))) print("Invalid Name.");
        // Flat Coordinates
        Coordinates coordinates = parseCoordinates();
        // studentsCount
        long studentsCount;
        while (!StudyGroup.validateStudentsCount(studentsCount = parseLong("StudentsCount", "long, min 1"))) {
            print("Invalid StudentsCount");
        }
        //int expelledStudents
        int expelledStudents;
        while (!StudyGroup.validateExpelledStudents(expelledStudents = parseInt("ExpelledStudents", "int, min 1"))) {
            print("Invalid ExpelledStudents");
        }
        //long shouldBeExpelled
        long shouldBeExpelled;
        while (!StudyGroup.validateShouldBeExpelled(shouldBeExpelled = parseLong("ShouldBeExpelled", "long, min 1"))) {
            print("Invalid StudentsCount");
        }
        //FormOfEducation formOfEducation
        FormOfEducation formOfEducation;
        String formOfEducationValues = Arrays.asList(FormOfEducation.values()).toString();
        while (!StudyGroup.validateFormOfEducation(formOfEducation = parseEnum(FormOfEducation.class, "FormOfEducation " + formOfEducationValues, "not null"))) {
            print("Invalid FormOfEducation " + formOfEducationValues);
        }

        Person groupAdmin = parsePerson();

        return new StudyGroup(name, coordinates, studentsCount,
                expelledStudents,shouldBeExpelled,formOfEducation,groupAdmin);
    }
}
