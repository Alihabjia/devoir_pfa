package com.emsi.web.managed;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import com.emsi.dao.EmployeeDao;
import com.emsi.model.Employee;

@ManagedBean
@SessionScoped
public class RegisterEmployee {

	private Employee employee=new Employee();

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public String register() {
		EmployeeDao dao = new EmployeeDao();
		dao.register(employee);
		// Add message
		FacesContext.getCurrentInstance().addMessage(
				null,
				new FacesMessage("The Employee "
						+ this.employee.getEmployeeName()
						+ " Is Registered Successfully"));
		return "";
	}
}
