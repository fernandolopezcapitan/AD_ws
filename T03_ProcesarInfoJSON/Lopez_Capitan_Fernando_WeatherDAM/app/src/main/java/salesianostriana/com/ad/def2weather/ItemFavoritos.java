package salesianostriana.com.ad.def2weather;

/**
 * Created by flopez on 12/11/2015.
 */
public class ItemFavoritos {

        private String ciudad;

        public ItemFavoritos(String nombre) {
            this.ciudad = nombre;
        }

        public String getCiudad() {
            return ciudad;
        }

        public void setCiudad(String ciudad) {
            this.ciudad = ciudad;
        }

}
