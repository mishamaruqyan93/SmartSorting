package org.example.sorting;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Task1Impl implements IStringRowsListSorter {
    public static final IStringRowsListSorter INSTANCE = new Task1Impl();

    private Task1Impl() {
    }

    /**
     * @return the singleton instance of Task1Impl
     */
    public static IStringRowsListSorter getInstance() {
        return INSTANCE;
    }

    /**
     * Сортирует список строковых массивов по указанному столбцу.
     *
     * @param rows        список строковых массивов
     * @param columnIndex индекс столбца, по которому осуществляется сортировка
     */
    @Override
    public void sort(List<String[]> rows, int columnIndex) {
        String[] strings = rows.get(columnIndex);
        Arrays.asList(strings).sort(Comparator.nullsFirst(Comparator.comparingInt(Task1Impl::countSubstrings))
                .thenComparing(Task1Impl::compareSubstrings));
    }

    /**
     * Возвращает количество подстрок в заданной строке.
     *
     * @param str строка, в которой нужно подсчитать подстроки
     * @return количество подстрок
     */
    private static int countSubstrings(String str) {
        return str.split("\\D+").length;
    }

    /**
     * Сравнивает две строки на основе их подстрок.
     *
     * @param str1 первая строка для сравнения
     * @param str2 вторая строка для сравнения
     * @return целочисленное значение, указывающее результат сравнения:
     * - отрицательное значение, если str1 меньше str2
     * - ноль, если str1 равна str2
     * - положительное значение, если str1 больше str2
     */
    private static int compareSubstrings(String str1, String str2) {
        String[] substrings1 = str1.split("\\D+");
        String[] substrings2 = str2.split("\\D+");

        for (int i = 0; i < Math.min(substrings1.length, substrings2.length); i++) {
            String substring1 = substrings1[i];
            String substring2 = substrings2[i];

            if (isNumeric(substring1) && isNumeric(substring2)) {
                int comparison = getComparison(substring1, substring2);
                if (comparison != 0) {
                    return comparison;
                }
            }
            if (checkNumericSeparate(substring1, substring2) == 0) {
                int comparison = substring1.compareTo(substring2);
                if (comparison != 0) {
                    return comparison;
                }
            }
        }
        return Integer.compare(substrings1.length, substrings2.length);
    }

    /**
     * Сравнивает две числовые подстроки как целые числа.
     *
     * @param substring1 первая числовая подстрока
     * @param substring2 вторая числовая под
     */
    private static int getComparison(String substring1, String substring2) {
        int num1 = Integer.parseInt(substring1);
        int num2 = Integer.parseInt(substring2);
        int comparison = Integer.compare(num1, num2);
        return comparison;
    }

    /**
     * Проверяет, является ли строка числовой.
     *
     * @param str строка для проверки
     * @return {@code true}, если строка является числовой иначе {@code false}.
     */
    private static boolean isNumeric(String str) {
        return str.matches("\\d+");
    }

    private static int checkNumericSeparate(String substring1, String substring2) {
        if (isNumeric(substring1)) {
            return -1;
        } else if (isNumeric(substring2)) {
            return 1;
        } else return 0;
    }
}