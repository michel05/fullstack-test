import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { MusicListService } from '../music-list/music-list.service';
import { MusicService } from '../music.service';
import { Music } from '../music/music';

@Component({
  selector: 'mf-search',
  templateUrl: './search.component.html'
})
export class SearchComponent implements OnInit {

  cityForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private musicService: MusicService,
    private musicListService: MusicListService,
    private router: Router) { }

  ngOnInit(): void {
    this.cityForm = this.formBuilder.group({
      city: ['', Validators.required]
    });
  }

  listMusics(): void {
    const city = this.cityForm.get('city').value;
    this.musicService
      .listMusicsByCity(city)
      .subscribe(
        musics => this.musicListService.setMusics(musics),
        err => console.log(err)
      );
  }

}
