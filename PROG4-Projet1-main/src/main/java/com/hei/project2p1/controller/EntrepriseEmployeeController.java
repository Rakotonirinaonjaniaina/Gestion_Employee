package com.hei.project2p1.controller;

import com.hei.project2p1.modele.Entreprise;
import com.hei.project2p1.service.EntrepriseService;
import com.hei.project2p1.modele.Employee;
import com.hei.project2p1.service.EmployeeService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.Base64;
import java.util.Comparator;
import java.util.List;

@Controller
@AllArgsConstructor
public class EntrepriseEmployeeController {

    private final EntrepriseService entrepriseService;
    private final EmployeeService employeeService;

    @ModelAttribute("entreprise")
    public Entreprise createEntrepriseModel() {
        return entrepriseService.getEntrepriseDetails();
    }

    @GetMapping("/")
    public String showEntrepriseDetails(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        model.addAttribute("newEmployee", new Employee());
        return "index";
    }

    @PostMapping("/saveEntreprise")
    public String saveEntreprise(@ModelAttribute("entreprise") Entreprise entreprise) {
        entrepriseService.saveEntreprise(entreprise);
        return "redirect:/";
    }

    @PostMapping("/addEmployee")
    public String addEmployee(@ModelAttribute("newEmployee") @Valid Employee employee,
                              BindingResult bindingResult,
                              @RequestParam("imageFile") MultipartFile imageFile,
                              Model model) throws IOException {
        if (bindingResult.hasErrors()) {
            List<Employee> employees = employeeService.getAllEmployees();
            model.addAttribute("employees", employees);
            return "index";
        }

        if (!imageFile.isEmpty()) {
            employee.setImage(imageFile.getBytes());
        }
        employeeService.saveEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/employeeTable")
    public String showEmployeeTable(Model model) {
        List<Employee> employees = employeeService.getAllEmployees();
        model.addAttribute("employees", employees);
        return "employee_table";
    }

    @GetMapping("/employeeDetails/{id}")
    public String viewEmployeeDetails(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            byte[] imageBytes = employee.getImage();
            String encodedImage = imageBytes != null ? Base64.getEncoder().encodeToString(imageBytes) : null;
            model.addAttribute("employee", employee);
            model.addAttribute("encodedImage", encodedImage);
            return "employee_details";
        } else {
            return "redirect:/";
        }
    }

    @GetMapping("/editEmployee/{id}")
    public String showEditEmployeeForm(@PathVariable Long id, Model model) {
        Employee employee = employeeService.getEmployeeById(id);
        if (employee != null) {
            model.addAttribute("employee", employee);
            return "edit_employee";
        } else {
            return "redirect:/";
        }
    }

    @PostMapping("/updateEmployee")
    public String updateEmployee(@ModelAttribute("employee") @Valid Employee employee,
                                 BindingResult bindingResult,
                                 @RequestParam("imageFile") MultipartFile imageFile) throws IOException {
        if (bindingResult.hasErrors()) {
            return "edit_employee";
        }

        if (!imageFile.isEmpty()) {
            employee.setImage(imageFile.getBytes());
        }
        employeeService.updateEmployee(employee);
        return "redirect:/";
    }

    @GetMapping("/deleteEmployee/{id}")
    public String deleteEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        return "redirect:/";
    }

    @GetMapping("/filterEmployees")
    public String filterEmployeesByAttributeAndValue(@RequestParam("attribute") String attribute,
                                                     @RequestParam("value") String value,
                                                     Model model) {
        List<Employee> filteredEmployees = employeeService.filterEmployeesByAttributeAndValue(attribute, value);
        model.addAttribute("employees", filteredEmployees);
        model.addAttribute("newEmployee", new Employee());
        return "employee_table";
    }

    @GetMapping("/exportEmployees")
    public ResponseEntity<ByteArrayResource> exportEmployeesToCSV() {
        List<Employee> filteredEmployees = employeeService.getAllEmployees();
        String csvData = generateCSVData(filteredEmployees);
        byte[] csvBytes = csvData.getBytes();
        ByteArrayResource resource = new ByteArrayResource(csvBytes);
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=employees.csv");

        return ResponseEntity.ok()
                .headers(headers)
                .contentLength(csvBytes.length)
                .contentType(MediaType.parseMediaType("application/csv"))
                .body(resource);
    }

    private String generateCSVData(List<Employee> employees) {
        StringBuilder csvData = new StringBuilder();
        csvData.append("First Name,Last Name,Birth Date,Gender,Phones,Address,Personal Email,Work Email,CIN Number,CIN Date of Delivery,Function,Children Count,Hire Date,Departure Date,Socio-Professional Category,CNAPS Number\n");
        for (Employee employee : employees) {
            csvData.append(employee.getFirstName()).append(",");
            csvData.append(employee.getLastName()).append(",");
            csvData.append(employee.getBirthDate()).append(",");
            csvData.append(employee.getGender()).append(",");
            csvData.append(String.join(";", employee.getPhones())).append(",");
            csvData.append(employee.getAddress()).append(",");
            csvData.append(employee.getPersonalEmail()).append(",");
            csvData.append(employee.getWorkEmail()).append(",");
            csvData.append(employee.getCINNumber()).append(",");
            csvData.append(employee.getCINDateOfDelivery()).append(",");
            csvData.append(employee.getFunction()).append(",");
            csvData.append(employee.getChildrenCount()).append(",");
            csvData.append(employee.getHireDate()).append(",");
            csvData.append(employee.getDepartureDate()).append(",");
            csvData.append(employee.getSocioProfessionalCategory()).append(",");
            csvData.append(employee.getCNAPSNumber()).append("\n");
        }
        return csvData.toString();
    }
}
