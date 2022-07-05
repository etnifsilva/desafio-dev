CREATE SCHEMA   mytest_schema;

CREATE TABLE mytest_schema.store_transaction (
	id varchar NOT NULL,
	transaction_type varchar(13) NOT NULL,
	event_date date NOT NULL,
	amount numeric NOT NULL,
	cpf_number varchar(11) NOT NULL,
	card_number varchar(12) NOT NULL,
	transaction_hour timetz NOT NULL,
	store_owner_name varchar(14) NOT NULL,
	store_name varchar(19) NOT NULL,
	CONSTRAINT transaction_pk PRIMARY KEY (id)
);
