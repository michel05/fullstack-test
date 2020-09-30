import { Component, Input, OnInit } from '@angular/core';

@Component({
  selector: 'mf-music',
  templateUrl: './music.component.html',
  styleUrls: ['./music.component.css']
})
export class MusicComponent {

  @Input() artist = '';
  @Input() music = '';
  @Input() image = '';


}
