package com.rankingGame.rankingGame.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.rankingGame.rankingGame.model.Player;
import com.rankingGame.rankingGame.repository.PlayerRepository;

@Controller
public class PlayerController {
	
	@Autowired
	private PlayerRepository pr;
	
	@RequestMapping(value="/registerPlayer", method=RequestMethod.GET)
	public String form() {
		return "player/formPlayer";
	}
	
	@RequestMapping(value="/registerPlayer", method=RequestMethod.POST)
	public String form(@Valid Player player, BindingResult results, RedirectAttributes attributes) {
		if(results.hasErrors()) {
			attributes.addFlashAttribute("message", "Verifique os campos.");
		}
		else if(player.hasMoreVictoriesThanGames()) {
			attributes.addFlashAttribute("message", "O Número de vitória deve ser menor ou igual ao número de partidas.");
		}
		else {
			pr.save(player);
			attributes.addFlashAttribute("message", "Cadastro concluído com sucesso.");
		}
		return "redirect:/registerPlayer";
	}
	
	@RequestMapping("/players")
	public ModelAndView PlayersList() {
		ModelAndView mv = new ModelAndView("index");
		Iterable<Player> players = pr.findAllByOrderByVictoriesDesc();
		mv.addObject("players", players);
		return mv;
	}
	
	@RequestMapping("/delete")
	public String deletePlayer(long cod) {
		Player player = pr.findByCod(cod);
		pr.delete(player);
		return "redirect:/players"; 
	}
	
	@RequestMapping("/addV")
	public String addVictory(long cod) {
		Player player = pr.findByCod(cod);
		if(player.getVictories()+1 <= player.getNumberOfGames() ) {
			player.setVictories(player.getVictories() + 1);
			pr.save(player);
		}
		return "redirect:/players";
	}
	
	@RequestMapping("/addG")
	public String addGame(long cod) {
		Player player = pr.findByCod(cod);
		player.setNumberOfGames(player.getNumberOfGames() + 1);
		pr.save(player);
		return "redirect:/players";
	}
	
	
}
