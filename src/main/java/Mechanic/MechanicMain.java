package Mechanic;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MechanicMain {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        ArrayList<Customer> customers = readCustomersFromXml("src/main/resources/Customers.xml");

        int choice = -1;
        while (choice != 0) {
            switch (choice) {
                case 1 -> listCustomers(customers);
                case 2 -> addNewCustomer(customers);
                case 3 -> modifyCustomers(customers);
                case 4 -> removeCustomer(customers);
                case 5 -> System.out.println("MAIN PROBLEMS: ENGINE, CHASSIS, UNDERCARRIAGE, ELECTRONICS, OIL_CHANGE, TYRE_CHANGE, MOTOR_CONTROL_SYSTEM_CHANGE, DIAGNOSTIC, CHIP_TUNING, FLUID_CHANGE, PAINTING_AND_POLISHING, WINDOW_TINT, CHASSIS_FOILING, ENGINE BAY: ABS_MODULE, ABS_PUMP, AIR_FILTER,BATTERY, BRAKE_SERVO, CLIP, COOLANT_RESERVOIR, ECU, FUSE_BOX, FUSE, INTERCOOLER, POWER_STEERING_RESERVOIR, RADIATOR, RADIATOR_FAN, RELAY, WINDSHIELD_WASHER_RESERVOIR, ENGINE_BLOCK, CRANKSHAFT, POWER_STEERING_PUMP, PISTON_WITH_CONROD, PISTONG_RINGS, CRANKSHAFT_BEARING_CAP, ROD_CAP, ENGINE_HEAD, INTAKE_MANIFOLD, EXHAUST_MANIFOLD, CARBURATOR, CAMSHAFT, CAM_GEAR, TIMING_CHAIN, TIMING_COVER, AIR_FILTER_BASE, AIR_FILTER_COVER, VALVE_PUSH_ROD, ROCKER_ARM, ENGINE_HEAD_COVER, CRANKSHAFT_PULLEY, WATER_PUMP, WATER_PUMP_PULLEY, ARM, TURBO, ERG_VALVE, SERPENTINE_BELT, OIL_PAN, FLYWHEEL, ALTERNATOR, INGITION_COIL, IGNITION_WIRES, SPARK_PLUG, IGNITION_DISTRIBUTOR, IGNITION_DISTRIBUTOR_ROTOR, IGNITION_DISTRIBUTOR_CAP, FRONT SUSPENSION: CLUTCH_PLATE, CLUTCH_PRESSURE_PLATE, CLUTCH_RELEASE_BEARING, DRIVE_SHAFT, STARTER, BRAKE_CALIPER_CYLINDER, BRAKE_CALIPER, BRAKE_DISC_VENTILATED, BRAKE_PADS, FRONT_AXLE_KNUCKLE_COVER, FRONT_SHOCK_ABSORBER, FRONT_SHOCK_ABSORBER_CAP, FRONT_SPRING, FRONT_STEERING_KNUCKLE, FRONT_SUSPENSION_CROSSMEMBER, FRONT_SWAY_BAR, FRONT_WHEEL_HUB, INNER_TIE_ROD, OUTER_TIE_ROD, LOWER_SUSPENSION_ARM, RUBBER_BUSHING, STEERING_RACK, SWAY_BAR_FRONT_END_LINK, WHEEL_HUB_BEARING, WHEEL_HUB_CAP, BRAKE_DISC, REAR SUSPENSION: REAR_AXLE_KNUCKLE_HOUSING, REAR_DRIVE_AXLE, REAR_SHOCK_ABSORBER, REAR_SHOCK_ABSORBER_CAP, REAR_SPRING, REAR_SUSPENSION_ARM, REAR_SUSPENSION_CROSSMEMBER, REAR_SUSPENSION_UPPER_ARM, REAR_SWAY_BAR, REAR_WHEEL_HUB, SMALL_RUBBER_BUSHING, SPRING_CAP, SWAY_BAR_REAR_END_LINK, WHEEL_HUB, FUEL: FUEL_FILTER, FUEL_PUMP, FUEL_TANK, EXHAUST: CATALYTIC_CONVERTER, FRONT_EXHAUST_PIPE, MIDDLE_MUFFLER, REAR_MUFFLER;");
            }
            System.out.println("""
                    1 - List customers\r
                    2 - Add new customer\r
                    3 - Modify a customer\r
                    4 - Delete a customer\r
                    5 - Enumerate all selectable problem\r
                    0 - Save and Exit\r
                    Enter the number here:\040""");
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice < 0 || choice > 5) {
                    System.out.println("Invalid option.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid option.");
                scanner.nextLine();
            }
        }

        saveCustomersToXml(customers, "src/main/resources/Customers.xml");
    }
    public static ArrayList<Customer> readCustomersFromXml(String filepath) {
        ArrayList<Customer> customers = new ArrayList<>();
        try {
            DocumentBuilderFactory documentBuilderFactory =
                    DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder =
                    documentBuilderFactory.newDocumentBuilder();
            Document document = documentBuilder.parse(filepath);

            Element rootElement = document.getDocumentElement();
            NodeList childNodesList = rootElement.getChildNodes();

            int numberOfElementNodes = 0;
            Node node;
            for (int i = 0; i < childNodesList.getLength(); i++) {
                node = childNodesList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    numberOfElementNodes++;
                    NodeList childNodesOfUserTag = node.getChildNodes();
                    String customername = "", phonenumber = "", brand = "", licensePlate = "", yearOfManufacture = "",
                            cost = "", problem = "";
                    for (int j = 0; j < childNodesOfUserTag.getLength(); j++) {
                        if (childNodesOfUserTag.item(j).getNodeType() == Node.ELEMENT_NODE) {
                            switch (childNodesOfUserTag.item(j).getNodeName()) {
                 case "customername" -> customername = childNodesOfUserTag.item(j).getTextContent();
                                case "phonenumber" -> phonenumber = childNodesOfUserTag.item(j).getTextContent();
                                case "brand" -> brand = childNodesOfUserTag.item(j).getTextContent();
                                case "licenseplate" -> licensePlate = childNodesOfUserTag.item(j).getTextContent();
                                case "yearOfManufacture" ->yearOfManufacture = childNodesOfUserTag.item(j).getTextContent();
                                case "cost" -> cost = childNodesOfUserTag.item(j).getTextContent();
                                case "problem" -> problem = childNodesOfUserTag.item(j).getTextContent();

                            }
                        }
                    }
                    customers.add(new Customer(customername, Integer.parseInt(phonenumber), brand, licensePlate,
                            Integer.parseInt(yearOfManufacture), Integer.parseInt(cost), Problems.valueOf(problem)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return customers;
    }


    private static void modifyCustomers(ArrayList<Customer> customers) {
        System.out.print("Please enter the name of the customer you wish to modify: ");
        String customername = scanner.nextLine();
        for (Customer customer : customers) {
            if (customer.getCustomerName().equals(customername)) {
                inputyearOfManufacture();
                System.out.println("Please enter the car's brand: ");
                String brand = scanner.nextLine();
                System.out.println("Please enter the car's license plate (AAA - 111) : ");
                String licenseplate = scanner.nextLine();
                Problems problems = inputProblem();
                System.out.println("Please specify the cost of servicing: ");
                int cost = scanner.nextInt();
                System.out.print("Please enter the customer's phone number: +36 ");
                int phonenumber = scanner.nextInt();
                customers.set(customers.indexOf(customer), new Customer(customername, phonenumber,
                        brand, licenseplate, customer.getYearOfManufacture(),
                        cost, problems));
                System.out.println("Customer was successfully modified.");
                return;
            }
        }
        System.out.println("The name of the customer was invalid or there is no customer with this name.");
    }

    private static void removeCustomer(ArrayList<Customer> customers) {
        System.out.print("Please enter the name of the customer who you want to removed: ");
        String name = scanner.nextLine();
        for (Customer customer : customers) {
            if (customer.getCustomerName().equals(name)) {
                        customers.remove(customer);
                        System.out.println("Customer is successfully removed.");
                        return;
                    }
                }
        System.out.println("There is no customer with this name.");
    }

    private static void addNewCustomer(ArrayList<Customer> customers) {
        System.out.print("Please enter the name of the new customer: ");
        String name = scanner.nextLine();
        int yearOFManufacture = inputyearOfManufacture();
        System.out.println("Please enter the car's brand: ");
        String brand = scanner.nextLine();
        System.out.println("Please enter the car's license plate: ");
        String licenseplate = scanner.nextLine();
        Problems problems = inputProblem();
        System.out.println("Please specify the cost of servicing: ");
        int cost = scanner.nextInt();
        System.out.print("Please enter the customer's phone number: +36 ");
        int phonenumber = scanner.nextInt();
        customers.add(new Customer(name, phonenumber, brand, licenseplate,yearOFManufacture, cost, problems));
    }


    private static int inputyearOfManufacture() {
        int yearOfManufacture = 0;
        while (1884 > yearOfManufacture || 2022 < yearOfManufacture) {
            try {
                System.out.print("Please enter the car's year of manufacture: ");
                yearOfManufacture = scanner.nextInt();
                scanner.nextLine();
                if (yearOfManufacture < 1884 || yearOfManufacture > 2022) {
                    System.out.println("Manufacture year cannot be less than" +
                            " 1884 or greater 2022.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Not valid manufacture year.");
                scanner.nextLine();
            }
        }
        return yearOfManufacture;
    }

    private static Problems inputProblem() {
        Problems problems = Problems.ENGINE;
        String problemString = "";
        while (problemString.isEmpty()) {
            try {
                System.out.print("Please enter problem of new customer (use '_' and not (-,:,') and space): ");
                problemString = scanner.nextLine();
                problems = Problems.valueOf(problemString.toUpperCase());
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid problem.");
                problemString = "";
            }
        }
        return problems;
    }

    private static void listCustomers(ArrayList<Customer> customers) {
        System.out.println(customers);
    }

    public static void saveCustomersToXml(ArrayList<Customer> customers, String filepath) {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element rootElement = document.createElement("Customers");
            document.appendChild(rootElement);

            for (Customer customer : customers) {
                Element userElement = document.createElement("customer");
                rootElement.appendChild(userElement);
                createChildElement(document, userElement, "customername", customer.getCustomerName());
                createChildElement(document, userElement, "phonenumber",
                        String.valueOf(customer.getPhonenumber()));
                createChildElement(document, userElement, "brand", customer.getBrand());
                createChildElement(document, userElement, "licensePlate", customer.getLicensePLate());
                createChildElement(document, userElement, "license",
                        String.valueOf(customer.getLicensePLate()));
                createChildElement(document, userElement, "cost",
                        String.valueOf(customer.getCost()));
                createChildElement(document, userElement, "problem", customer.getProblems().toString());
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();

            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new FileOutputStream(filepath));

            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createChildElement(Document document, Element parent, String tagName, String value) {
        Element element = document.createElement(tagName);
        element.setTextContent(value);
        parent.appendChild(element);
    }

}