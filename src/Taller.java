import java.util.ArrayList;

public class Taller {
    private String nit;
    private String nombre;
    private String direccion;
    private static Taller instancia;

    private Cliente[] listClientes;

    public Taller(String nit, String nombre, String direccion) {
        this.nit=nit;
        this.nombre=nombre;
        this.direccion=direccion;
        listClientes = new Cliente[30];
    }

    public static Taller getInstancia(){
        if(instancia==null){
            ArrayList<Bicicleta> bicicletasIniciales = new ArrayList<>();
            ArrayList<Cliente> clientesIniciales = new ArrayList<>();
            ArrayList<Mecanico> mecanicosIniciales = new ArrayList<>();

            instancia = new Taller("123", "Taller de Bicicletas", "Carrera 12, calle 9");
        }
        return instancia;
    }

    //CRUD Registrar cliente
    public boolean registrarCliente(String id, String nombre, String numeroContacto, String direccion){
        Cliente cliente = new Cliente(id, nombre, numeroContacto, direccion);
        if(buscarClienteById(id)==-1){
            for(int i=0; i<listClientes.length; i++){
                if(listClientes[i]==null){
                    listClientes[i]=cliente;
                    return true;
                }
            }
        }
        return false;
    }
    public int buscarClienteById(String id){
        for(int i=0; i<listClientes.length; i++){
            if(listClientes[i]!=null && listClientes[i].getId().equals(id)){
                return i;
            }
        }
        return -1;
    }
    // Delete - Eliminar cliente
    public boolean eliminarCliente(String id) {
        int posicion = buscarClienteById(id);
        if (posicion != -1) {
            listClientes[posicion] = null;
            return true;
        }
        return false;
    }
}
