import { Injectable } from '@angular/core';
import { Music } from '../music/music';

@Injectable({providedIn: 'root'})
export class MusicListService {

    musics: Music[] = [];

    constructor() { }

    setMusics(musics: Music[]): void {
        this.musics = musics;
    }

    getMusics(): Music[] {
        return this.musics;
    }
}
