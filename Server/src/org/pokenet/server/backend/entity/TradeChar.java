package org.pokenet.server.backend.entity;

import org.pokenet.server.battle.Pokemon;

/**
 * Represents an NPC that wants to trade Pokemon
 * @author shadowkanji
 *
 */
public class TradeChar extends NonPlayerChar implements Tradeable {
	private Trade m_trade = null;
	private boolean m_tradeAccepted = false;
	/*
	 * Requested Pokemon data
	 */
	private String m_requestedSpecies = "";
	/*
	 * Offered Pokemon data
	 */
	private Pokemon [] m_party;
	
	/**
	 * Constructor
	 */
	public TradeChar() {
		setBadge(-1);
		setHealer(false);
		setPartySize(0);
	}
	
	/**
	 * Sets the Pokemon the NPC wants
	 * @param species
	 * @param level
	 * @param nature
	 */
	public void setRequestedPokemon(String species, int level, String nature) {
		m_requestedSpecies = species;
		//TODO: Add support for levels and natures
	}
	
	/**
	 * Sets the Pokemon the NPC offers
	 * @param species
	 * @param level
	 */
	public void setOfferedSpecies(String species, int level) {
		m_party = new Pokemon[1];
		m_party[0] = Pokemon.getRandomPokemon(species, level);
	}
	
	@Override
	public void talkToPlayer(PlayerChar p) {
		if(m_trade == null) {
			/* Can trade */
			m_trade = new Trade(this, p);
			p.setTrade(m_trade);
			m_trade.setOffer(this, 0, 0);
		} else {
			/* Can't trade */
			//TODO: Send message to player saying they cannot trade right now
		}
	}

	public boolean acceptedTradeOffer() {
		return m_tradeAccepted;
	}

	public void cancelTrade() {
		m_trade.endTrade();
	}

	public void cancelTradeOffer() {}

	public void finishTrading() {
		m_trade = null;
		m_tradeAccepted = false;
	}

	public int getMoney() {
		return 999999;
	}

	public Pokemon[] getParty() {
		return m_party;
	}

	public Trade getTrade() {
		return m_trade;
	}

	public boolean isTrading() {
		return m_trade != null;
	}

	public void receiveTradeOffer(TradeOffer[] o) {
		if(o[0].getInformation().equalsIgnoreCase(m_requestedSpecies)) {
			//This is the Pokemon the TradeChar wanted
			setTradeAccepted(true);
		}
	}

	public void receiveTradeOfferCancelation() {}

	public void setMoney(int money) {}

	public void setTrade(Trade t) {
		m_trade = t;
	}

	public void setTradeAccepted(boolean b) {
		m_tradeAccepted = b;
		if(b) {
			m_trade.checkForExecution();
		}
	}

	public String getIpAddress() {
		return "";
	}
}
