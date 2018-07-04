package br.com.agilizeware.pageable.memory.common;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Util {

	public static boolean matches(String regex, String value) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(value);
		return matcher.find();
	}

	public static Field getField(Object object, String name) {

		if (isNullOrEmpty(name) || object == null) {
			return null;
		}

		return getField2(object.getClass(), name.split("[.]"), 0);

	}

	public static String getValueString(Object obj) {
		if (isNotNull(obj)) {
			return obj.toString();
		}
		return "";
	}

	public static boolean isNullOrEmpty(Object o) {
		return o == null || o.equals("");
	}

	public static boolean isNotNull(Object o) {
		return o != null && !o.toString().equals("");
	}

	public static boolean isListNotNull(List col) {
		return col != null && !col.isEmpty();
	}

	public static boolean isNullOrEmpty(Collection<?> value) {
		return value == null || value.isEmpty() || value.size() == 0;
	}

	public static boolean isNullOrEmpty(String value) {
		return value == null || value.length() == 0;
	}

	public static boolean isNullOrEmpty(Object[] value) {
		return value == null || value.length == 0;
	}

	private static Field getField2(Class<?> clazz, String[] names, int i) {

		if (isNullOrEmpty(names) || clazz == null) {
			return null;
		}

		Field field = null;

		try {
			field = clazz.getDeclaredField(names[i]);
		} catch (NoSuchFieldException | SecurityException e2) {
			try {
				field = clazz.getSuperclass().getDeclaredField(names[i]);
			} catch (NoSuchFieldException | SecurityException e3) {
				throw new RuntimeException(e3);
			}
		}

		if (names.length == ++i) {
			return field;
		} else {
			return getField2(field.getType(), names, i);
		}

	}

	public static String toString(Date date) {
		return sdf.format(date);
	}

	public static Object getFieldValue(Object object, String name) {

		if (isNullOrEmpty(name) || object == null) {
			return null;
		}

		return getFieldValue2(object, name.split("[.]"), 0);

	}

	private static Object getFieldValue2(Object object, String[] names, int i) {

		if (isNullOrEmpty(names) || object == null) {
			return null;
		}

		Field field = null;

		try {
			field = object.getClass().getDeclaredField(names[i]);
		} catch (Exception e2) {
			try {
				field = object.getClass().getSuperclass().getDeclaredField(names[i]);
			} catch (Exception e3) {
				throw new RuntimeException(e3);
			}
		}
		// torna acessivel o field para manipulação
		field.setAccessible(true);
		try {
			if (names.length == ++i) {

				return field.get(object);

			} else {
				return getFieldValue2(field.get(object), names, i);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

	private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.000");

	public static Boolean detectChange(Object before, Object after) {
		Boolean flag = Boolean.FALSE;

		if (before != null && !before.equals(after)) {
			flag = Boolean.TRUE;
		} else if (before == null && after != null) {
			flag = Boolean.TRUE;
		}

		return flag;
	}

	public static String onlyAphaNumeric(String value) {
		return value.replaceAll("[^A-Za-z0-9]", "");
	}

	public static <T> List<T> safeList(List<T> other) {
		return other == null ? Collections.EMPTY_LIST : other;
	}
}
