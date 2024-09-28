/*
 * =============================================================================
 * 
 *   Copyright (c) 2011-2014, The THYMELEAF team (http://www.thymeleaf.org)
 * 
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 * 
 *       http://www.apache.org/licenses/LICENSE-2.0
 * 
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 * 
 * =============================================================================
 */
package org.thymeleaf.examples.springboot3.biglist.webflux.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.spring6.context.webflux.ReactiveDataDriverContextVariable;
import reactor.core.publisher.Flux;
import org.thymeleaf.examples.springboot3.biglist.webflux.business.PlaylistEntry;
import org.thymeleaf.examples.springboot3.biglist.webflux.business.repository.PlaylistEntryRepository;


@Controller
public class ThymeleafController {


    private PlaylistEntryRepository playlistEntryRepository;



    public ThymeleafController() {
        super();
    }


    @Autowired
    public void setPlaylistEntryRepository(final PlaylistEntryRepository playlistEntryRepository) {
        this.playlistEntryRepository = playlistEntryRepository;
    }



    @RequestMapping({"/", "/thymeleaf"})
    public String index() {
        return "thymeleaf/index";
    }


    @RequestMapping("/smalllist.thymeleaf")
    public String smallList(final Model model) {
        model.addAttribute("entries", this.playlistEntryRepository.findAllPlaylistEntries());
        return "thymeleaf/smalllist";
    }


    @RequestMapping("/biglist-datadriven.thymeleaf")
    public String bigListDataDriven(final Model model) {

        final Flux<PlaylistEntry> playlistStream = this.playlistEntryRepository.findLargeCollectionPlaylistEntries();
        // No need to fully resolve the Publisher! We will just let it drive
        model.addAttribute("dataSource", new ReactiveDataDriverContextVariable(playlistStream, 1000));

        return "thymeleaf/biglist-datadriven";

    }


    @RequestMapping("/biglist-chunked.thymeleaf")
    public String bigListChunked(final Model model) {

        // Will be async resolved by Spring WebFlux before calling the view
        final Flux<PlaylistEntry> playlistStream = this.playlistEntryRepository.findLargeCollectionPlaylistEntries();

        model.addAttribute("dataSource", playlistStream);

        return "thymeleaf/biglist-chunked";

    }


    @RequestMapping("/biglist-full.thymeleaf")
    public String bigListFull(final Model model) {

        // Will be async resolved by Spring WebFlux before calling the view
        final Flux<PlaylistEntry> playlistStream = this.playlistEntryRepository.findLargeCollectionPlaylistEntries();

        model.addAttribute("dataSource", playlistStream);

        return "thymeleaf/biglist-full";

    }

}
