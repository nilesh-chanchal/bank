package com.management.bank.entitiy;

import static com.management.bank.constants.ColumnConstants.ID;
import static com.management.bank.constants.TableConstants.ALLOCATION_SIZE;
import static com.management.bank.constants.TableConstants.CUSTOMER_CUST_ID_SEQ;
import static com.management.bank.constants.TableConstants.CUSTOMER_TABLE;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@Entity
@Table(name = CUSTOMER_TABLE)
public class Customer extends ProfileBase {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = CUSTOMER_CUST_ID_SEQ)
	@SequenceGenerator(name = CUSTOMER_CUST_ID_SEQ, sequenceName = CUSTOMER_CUST_ID_SEQ, allocationSize = ALLOCATION_SIZE)
	@Column(name = ID)
	private long id;

	@OneToMany(fetch = FetchType.EAGER, mappedBy="id")
	@JsonIgnore
	private List<Beneficiary> beneficiaries;

}
