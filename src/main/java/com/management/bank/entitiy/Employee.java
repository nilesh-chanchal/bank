package com.management.bank.entitiy;

import static com.management.bank.constants.ColumnConstants.ID;
import static com.management.bank.constants.TableConstants.ALLOCATION_SIZE;
import static com.management.bank.constants.TableConstants.EMPLOYEE_EMP_ID_SEQ;
import static com.management.bank.constants.TableConstants.EMPLOYEE_TABLE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = EMPLOYEE_TABLE)
public class Employee extends ProfileBase {

	@SuppressWarnings("unused")
	private static final long serialVerionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = EMPLOYEE_EMP_ID_SEQ)
	@SequenceGenerator(name = EMPLOYEE_EMP_ID_SEQ, sequenceName = EMPLOYEE_EMP_ID_SEQ, allocationSize = ALLOCATION_SIZE)
	@Column(name = ID)
	private long id;

}
