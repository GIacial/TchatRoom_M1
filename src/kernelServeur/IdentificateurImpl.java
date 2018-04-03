package kernelServeur;

import java.io.Serializable;
import java.util.HashMap;
import kernelMsg.PseudoNonLibreException;
import kernelMsg.PseudoNotFoundException;

/**
 * l'indentificateur des utilisateur du serveur
 */
public class IdentificateurImpl implements Serializable{

	/**
	 * Collection (id,Pseudo) des pseudo existant
	 */
	private final HashMap<Integer,String> pseudos;
        
        public IdentificateurImpl(){
            this.pseudos = new HashMap<Integer,String>();
        }

	/**
	 * ce connect avec un pseudo au system
	 * Renvoi un String qui est l'identificateur pour le serveur(diff du serveur
	 * Peut renvoyer l'exceptionPseudoNonLibreException
	 * @param pseudo le pseudo de la personne
         * @return 
         * @throws kernelMsg.PseudoNonLibreException
	 */
	synchronized public int connexion(String pseudo) throws PseudoNonLibreException{
                if(this.pseudos.containsValue(pseudo)){
                    throw new PseudoNonLibreException();
                }
		Integer key = pseudo.hashCode();
                this.pseudos.put(key, pseudo);
                return key;
	}

	/**
	 * libere le pseudo du client
	 * peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identificateur du clinet
         * @throws kernelMsg.PseudoNotFoundException
	 */
	synchronized public void disconnect(int id) throws PseudoNotFoundException {
		if(!this.pseudos.containsKey(id)){
                    throw new PseudoNotFoundException();
                }
                this.pseudos.remove(id);
	}

	/**
	 * donne le pseudo qui correspond a id
	 * @param id identificateur du client
         * @throws kernelMsg.PseudoNotFoundException
	 */
	 public String getPseudo(int id)throws PseudoNotFoundException {
		if(!this.pseudos.containsKey(id)){
                    throw new PseudoNotFoundException();
                }
                return (String) this.pseudos.get(id);
	}

}