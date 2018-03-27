package kernelServeur;

/**
 * l'indentificateur des utilisateur du serveur
 */
public class IdentificateurImpl {

	/**
	 * Collection (id,Pseudo) des pseudo existant
	 */
	private int Pseudos;

	/**
	 * ce connect avec un pseudo au system
	 * Renvoi un string qui est l'identificateur pour le serveur(diff du serveur
	 * Peut renvoyer l'exceptionPseudoNonLibreException
	 * @param pseudo le pseudo de la personne
	 */
	public string connexion(string pseudo) {
		// TODO - implement IdentificateurImpl.connexion
		throw new UnsupportedOperationException();
	}

	/**
	 * libere le pseudo du client
	 * peut renvoyer l'exception PseudoNotFoundException
	 * @param id l'identificateur du clinet
	 */
	public void disconnect(string id) {
		// TODO - implement IdentificateurImpl.disconnect
		throw new UnsupportedOperationException();
	}

	/**
	 * donne le pseudo qui correspond a id
	 * @param id identificateur du client
	 */
	public string getPseudo(int id) {
		// TODO - implement IdentificateurImpl.getPseudo
		throw new UnsupportedOperationException();
	}

}