package UTIL_18_TreeMap;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) // Necessário para acessar em tempo de execução
@Target(ElementType.TYPE) // Define onde pode ser usada (classe, método, etc.)
@interface MinhaAnnotation {
	String valor();
}

@MinhaAnnotation(valor = "Exemplo de Annotation")
class MinhaClasse {
}

public class ClasAnnotation {

	public static void run_Main() {
		// Obtendo a annotation da classe
		Class<?> clazz = MinhaClasse.class;
		if (clazz.isAnnotationPresent(MinhaAnnotation.class)) {
			MinhaAnnotation annotation = clazz.getAnnotation(MinhaAnnotation.class);
			System.out.println("Valor da Annotation: " + annotation.valor());
		} else {
			System.out.println("Annotation não encontrada.");
		}
	}

}