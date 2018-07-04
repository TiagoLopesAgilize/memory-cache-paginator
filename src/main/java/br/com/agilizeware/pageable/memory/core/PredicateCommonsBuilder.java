package br.com.agilizeware.pageable.memory.core;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;

import org.apache.commons.collections4.Predicate;

import br.com.agilizeware.pageable.memory.common.FilterOperator;
import br.com.agilizeware.pageable.memory.common.Util;

/**
 * 
 * Nome: PredicateCommonsBuilder.java Propósito:
 * <p>
 * Classe responsável pela criação dos Predicates utilizados para filtros em
 * listas
 * </p>
 * 
 * @author Agilizeware <BR/>
 *         Equipe: Agilizeware - Software - São Paulo <BR>
 * @version: 1.7 Parâmetro de compilação insira os parâmetros de compilação
 *
 * @throws: caso
 *              a classe não lance exception retirar essa linha
 * @see: insira a referência Registro de Manutenção:26/02/2018 16:57:14 - Autor:
 *       Tiago Lopes - Responsável: Tiago Lopes - Criação
 * 
 */
public final class PredicateCommonsBuilder {

	/**
	 * 
	 * Constrói e retorna o predicate com base nos parametros passados.
	 * 
	 * @param param
	 * @param finalField
	 * @param operator
	 * @return
	 */
	public static Predicate makePredicate(final String paramName, final Object param, final Field finalField,
			final FilterOperator operator) {

		switch (operator) {
		case EQ:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null) {
							// faz acessível o atributo
							finalField.setAccessible(true);
							Object fieldValue = null;
							if (paramName.split("[.]").length > 1) {
								fieldValue = Util.getFieldValue(o, paramName);
							} else {
								fieldValue = finalField.get(o);
							}

							Method m = finalField.getType().getMethod("equals", Object.class);
							if (fieldValue != null) {
								return (Boolean) m.invoke(fieldValue,
										createGenericObjectTypeInstance(param, finalField));
							}
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return false;
				}
			};

		case CONTAINS:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null) {
							String valor = null;
							Object fieldValue = getFieldValue(paramName, finalField, o);

							if (fieldValue == null) {
								return Boolean.FALSE;
							}
							valor = fieldValue.toString().toLowerCase();

							return valor.contains(param.toString().toLowerCase());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return false;
				}
			};
		// TODO talvez tenha que ser com regex
		case LIKE:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null && !param.toString().trim().isEmpty()) {
							String valor = null;
							Object fieldValue = getFieldValue(paramName, finalField, o);

							if (fieldValue == null) {
								return Boolean.FALSE;
							}
							valor = fieldValue.toString().toLowerCase();

							return valor.contains(param.toString().toLowerCase());
						}
						return Boolean.TRUE;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};

		case STARTSWITH:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null && !param.toString().trim().isEmpty()) {
							String valor = null;
							Object fieldValue = getFieldValue(paramName, finalField, o);

							if (fieldValue == null) {
								return Boolean.FALSE;
							}

							valor = fieldValue.toString().toLowerCase();
							return valor.startsWith(param.toString().toLowerCase());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};
		case ENDSWITH:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null && !param.toString().trim().isEmpty()) {
							String valor = null;
							Object fieldValue = getFieldValue(paramName, finalField, o);

							if (fieldValue == null) {
								return Boolean.FALSE;
							}
							valor = fieldValue.toString().toLowerCase();
							return valor.endsWith(param.toString().toLowerCase());
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};
		case GE:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						Object fieldValue = getFieldValue(paramName, finalField, o);

						if (fieldValue == null) {
							return Boolean.FALSE;
						}

						Method m = finalField.getType().getMethod("compareTo", finalField.getType());

						// Convert a string para o tipo que o field é
						Object objectInstance = createGenericObjectTypeInstance(param, finalField);

						Integer valor = (Integer) m.invoke(fieldValue, objectInstance);

						return valor >= 0;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};
		case GT:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						Object fieldValue = getFieldValue(paramName, finalField, o);
						Method m = finalField.getType().getMethod("compareTo", finalField.getType());

						// Convert a string para o tipo que o field é
						Object objectInstance = createGenericObjectTypeInstance(param, finalField);

						Integer valor = (Integer) m.invoke(fieldValue, objectInstance);

						return valor > 0;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};
		case LE:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null && !param.toString().trim().isEmpty()) {
							Object fieldValue = getFieldValue(paramName, finalField, o);
							if (fieldValue == null) {
								return Boolean.FALSE;
							}

							Method m = finalField.getType().getMethod("compareTo", finalField.getType());
							// Convert a string para o tipo que o field é
							Object objectInstance = createGenericObjectTypeInstance(param, finalField);

							Integer valor = (Integer) m.invoke(fieldValue, objectInstance);

							return valor <= 0;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};

		case LT:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						if (param != null && !param.toString().trim().isEmpty()) {
							Object fieldValue = getFieldValue(paramName, finalField, o);
							if (fieldValue == null) {
								return Boolean.FALSE;
							}

							Method m = finalField.getType().getMethod("compareTo", finalField.getType());
							// Convert a string para o tipo que o field é
							Object objectInstance = createGenericObjectTypeInstance(param, finalField);

							Integer valor = (Integer) m.invoke(fieldValue, objectInstance);

							return valor < 0;
						}
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}

			};
		case IN:
			// TODO

		case ISEMPTY:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						Object fieldValue = getFieldValue(paramName, finalField, o);

						if (fieldValue != null) {
							return false;
						}

						if (fieldValue instanceof String) {
							return ((String) fieldValue).isEmpty();
						}

						if (fieldValue instanceof Collection) {
							return ((Collection) fieldValue).isEmpty();
						}

						return Boolean.TRUE;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};

		case ISNOTEMPTY:
			return new Predicate() {
				public boolean evaluate(Object o) {
					try {
						Object fieldValue = getFieldValue(paramName, finalField, o);

						if (fieldValue != null) {
							return true;
						}
						if (fieldValue instanceof String) {
							return !((String) fieldValue).isEmpty();
						}
						if (fieldValue instanceof Collection) {
							return !((Collection) fieldValue).isEmpty();
						}

						return Boolean.FALSE;
					} catch (Exception e) {
						e.printStackTrace();
					}
					return Boolean.FALSE;
				}
			};

		default:
			return null;
		}
	}

	/**
	 * Método responsável por criar uma nova instancia do tipo de objeto que o Field
	 * é utilizando-se do construtor que recebe como parametro um valor String,
	 * Exemplo public Integer(String valor). </br>
	 * </br>
	 * <b>Exemplo:</b></br>
	 * String valor = "1";</br>
	 * Field f = BaseDTO.getClass().getDeclaredField("id");</br>
	 * Object retorno = createGenericObjectTypeInstance(valor, f);</br>
	 * </br>
	 * A variavel 'retorno' conterá uma instancia da classe Integer com o valor 1.
	 * 
	 * @param param
	 *            Objeto que se quer converter para o tipo de objeto que o parametro
	 *            Field representa
	 * @param f
	 *            Field do tipo de objeto que vai ser utilizado o construtor para
	 *            conversão do param
	 * @return Object do tipo que o parametro Field representa
	 * 
	 * @throws NoSuchMethodException
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 */
	private static Object createGenericObjectTypeInstance(final Object param, Field f)
			throws NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException {

		Object objectInstance = null;
		// Se a class que o Field sendo comparado representa não é String é necessário
		// executar o construtor que recebe como parametro
		// uma String para converter o value para o tipo do Field em questão
		/*
		 * if (!f.getType().equals(java.lang.String.class) &&
		 * !f.getType().isAnnotationPresent(org.codehaus.jackson.map.annotate.
		 * JsonSerialize.class)) {
		 */
		if (!f.getType().equals(java.lang.String.class)) {
			Constructor cons = f.getType().getConstructor(String.class);
			objectInstance = (Object) cons.newInstance(new Object[] { param });
			return objectInstance;
		}

		return param;
	}

	/**
	 * 
	 * Retorna o valor do Field
	 * 
	 * @param paramName
	 *            Nome do parametro usado para determinar se busca de forma
	 *            recursiva o atributo sendo ele um relacionamento
	 * @param finalField
	 *            Field que representa o atributo que se quer retornar
	 * @param o
	 *            Objeto que contem o atributo
	 * @return
	 * @throws IllegalAccessException
	 */
	private static Object getFieldValue(final String paramName, final Field finalField, Object o)
			throws IllegalAccessException {
		Object fieldValue = null;
		// faz acessível o atributo
		finalField.setAccessible(true);
		if (paramName.split("[.]").length > 1) {
			fieldValue = Util.getFieldValue(o, paramName);
		} else {
			fieldValue = finalField.get(o);
		}
		return fieldValue;
	}
}
