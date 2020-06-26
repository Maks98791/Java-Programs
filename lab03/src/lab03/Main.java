/*Zadanie polega na zaprojektowaniu aplikacji pozwalaj¹cej na dokonywanie rejestracji 
 * pacjentów w przychodni. Zak³adamy, ¿e w aplikacji wykorzystane zostan¹ dostêpne 
 * struktury danych i pliki. Aplikacja powinna obs³u¿yæ nastêpuj¹ce zestawy danych:

- dane osobowe pacjentów (wystarczy imiê, nazwisko, pesel)
- dane lekarzy (wystarczy imiê, nazwisko, specjalizacja)
- gabinety (lekarze przyjmuj¹ w gabinetach)
- godziny przyjêæ (w kolejne dni tygodnia, w godzinach od do, co zadany interwa³:np.20min
- zarejestrowane wizyty (mo¿na dokonaæ rejestracji na wizytê)
- zakoñczone wizyty (by by³o wiadomo, które wizyty faktycznie siê zakoñczy³y)

Aplikacja powinna umo¿liwiaæ:

- wyszukanie wolnego terminu u danego lekarza
- zarejestrowanie i wyrejestrowanie pacjenta
- zapisanie wyniku wizyty po jej zakoñczeniu
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
