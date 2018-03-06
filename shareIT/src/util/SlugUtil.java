package util;

import java.text.Normalizer;  
import java.text.Normalizer.Form;  
import java.util.Locale;  
import java.util.regex.Pattern;  
  
public class SlugUtil {  
  
  private static final Pattern NONLATIN = Pattern.compile("[^\\w-]");  
  private static final Pattern WHITESPACE = Pattern.compile("[\\s]");  
  
  public static String makeSlug(String input) {  
	input = input.replaceAll("Đ", "d");
	String nowhitespace = WHITESPACE.matcher(input).replaceAll("-");  
    String normalized = Normalizer.normalize(nowhitespace, Form.NFD).toLowerCase();  
    String slug = NONLATIN.matcher(normalized).replaceAll("").replaceFirst("đ", "d");
    return slug.toLowerCase(Locale.ENGLISH);  
  }  
  
} 