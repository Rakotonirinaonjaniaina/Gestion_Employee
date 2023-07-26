package com.hei.project2p1.service;

import com.hei.project2p1.modele.Employee;
import com.hei.project2p1.repository.EmployeeRepository;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class EmployeeService {
    @PersistenceContext
    private EntityManager entityManager;

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public void saveEmployee(Employee employee) {
        entityManager.persist(employee);
    }

    public Employee getEmployeeById(Long id) {
        return employeeRepository.findById(id).orElse(null);
    }

    public void updateEmployee(Employee employee) {
        entityManager.merge(employee);
    }

    public void deleteEmployee(Long id) {
        employeeRepository.deleteById(id);
    }

    // Méthode pour filtrer et trier les employés
    // Méthode pour filtrer et trier les employés
    // Méthode pour filtrer et trier les employés
    public List<Employee> filterEmployeesByAttributeAndValue(String attribute, String value) {
        // Utiliser la spécification pour filtrer les employés en fonction de l'attribut et de la valeur
        Specification<Employee> spec = (root, query, builder) ->
                builder.like(builder.lower(root.get(attribute)), "%" + value.toLowerCase() + "%");

        // Définir le tri (dans cet exemple, tri par identifiant de manière ascendante)
        Sort sort = Sort.by(Sort.Direction.ASC, "id");

        // Utiliser la méthode findAll avec la spécification et le tri pour obtenir les employés filtrés et triés
        return employeeRepository.findAll(spec, sort);
    }

}
