package com.management.bank.constants;

/**
 * Define query constants.
 *
 */
public class QueryConstants {
	private QueryConstants() {
	}

	// Customer Repository Queries
	public static final String FIND_CUSTOMER_BY_NATIONAL_ID = "SELECT national_id_no FROM public.customer WHERE national_id_no =:national_id_no";

	public static final String NATIONAL_ID_KEY = "national_id_no";

	// Account Repository Queries
	public static final String FIND_ACCOUNT_BY_CUSTOMER_ID = "SELECT id FROM public.account WHERE cust_id =:customerId";

	public static final String CUSTOMER_ID_KEY = "customerId";

	// Transaction Repository Queries
	public static final String FIND_TRANSACTION_BY_ACCOUNT_ID = "SELECT * FROM public.transaction WHERE cust_id =:accountId";

	public static final String ACCOUNT_ID_KEY = "accountId";

	// Beneficiary Repository Queries
	public static final String FIND_BENEFICIARY_BY_ACCOUNT_DETAILS = "SELECT b.id FROM public.beneficiary b "
			+ "WHERE b.account_id =:accountId AND b.account_no =:accountNubmer AND b.ifsc =:ifsc AND b.bank_name =:bankName";

	public static final String ACCOUNT_NUMBER_KEY = "accountNubmer";

	public static final String IFSC_KEY = "ifsc";

	public static final String BANK_NAME_KEY = "bankName";
}
