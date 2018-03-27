package kernelServeur;

import java.util.HashMap;
import kernelMsg.PseudoNonLibreException;
import kernelMsg.PseudoNotFoundException;

/**
 * l'indentificateur des utilisateur du serveur
 */
public class IdentificateurImpl {

	/**
	 * Collection (id,Pseudo) des pseudo existant
	 */
	private final HashMap<String,String> pseudos;
        
        public IdentificateurImpl(){
            this.pseudos = new HashMap<String,String>();
        }

	/**
	 * ce connect avec un pseudo au system
	 * Renvoi un String qui est l'identificateur pour le serveur(diff du serveur
	 * Peut renvoyer l'exceptionPseudoNonLibreException
	 * @param pseudo le pseudo de la personne
         * @return 
         * @throws kernelMsg.PseudoNonLibreException
	 */
	synchronized public String connexion(String pseudo) throws PseudoNonLibreException{
                if(this.pseudos.containsValue(pseudo)){
                    throw new PseudoNonLibreException();
                }
		String key = ""+pseudo.hashCode();
                this.pseudos.put(key, pseudo);
                return key;
	}

	/**
	 * libere le pseudo du client
	 * peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identificateur du clinet
         * @throws kernelMsg.PseudoNotFoundException
	 */
	synchronized public void disconnect(String id) throws PseudoNotFoundException {
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
	 public String getPseudo(String id)throws PseudoNotFoundException {
		if(!this.pseudos.containsKey(id)){
                    throw new PseudoNotFoundException();
                }
                return (String) this.pseudos.get(id);
	}

}