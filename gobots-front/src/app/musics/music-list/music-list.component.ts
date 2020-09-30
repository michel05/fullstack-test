import { Component, OnChanges, OnInit, SimpleChanges } from '@angular/core';
import { Music } from '../music/music';
import { MusicListService } from './music-list.service';

@Component({
  selector: 'mf-music-list',
  templateUrl: './music-list.component.html',
  styleUrls: ['./music-list.component.css']
})
export class MusicListComponent {

  constructor(private musicListService: MusicListService) { }

  getMusics(): Music[] {
    return this.musicListService.getMusics();
  }

}
