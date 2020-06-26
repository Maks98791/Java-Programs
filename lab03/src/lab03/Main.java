/*Zadanie polega na zaprojektowaniu aplikacji pozwalaj�cej na dokonywanie rejestracji 
 * pacjent�w w przychodni. Zak�adamy, �e w aplikacji wykorzystane zostan� dost�pne 
 * struktury danych i pliki. Aplikacja powinna obs�u�y� nast�puj�ce zestawy danych:

- dane osobowe pacjent�w (wystarczy imi�, nazwisko, pesel)
- dane lekarzy (wystarczy imi�, nazwisko, specjalizacja)
- gabinety (lekarze przyjmuj� w gabinetach)
- godziny przyj�� (w kolejne dni tygodnia, w godzinach od do, co zadany interwa�:np.20min
- zarejestrowane wizyty (mo�na dokona� rejestracji na wizyt�)
- zako�czone wizyty (by by�o wiadomo, kt�re wizyty faktycznie si� zako�czy�y)

Aplikacja powinna umo�liwia�:

- wyszukanie wolnego terminu u danego lekarza
- zarejestrowanie i wyrejestrowanie pacjenta
- zapisanie wyniku wizyty po jej zako�czeniu
- przeszukiwanie rejestracji (w poszukiwaniu pacjenta)
*/

package lab03;

import java.util.Date;

public class Main {

	public static void main(String[] args) {
		
		Interface menu = new Interface();
		menu.Menu();
	}

}
