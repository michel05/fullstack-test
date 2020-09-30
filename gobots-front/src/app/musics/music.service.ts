import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Music } from './music/music';
import { environment } from 'src/environments/environment';

@Injectable({providedIn: 'root'})
export class MusicService {

    constructor(private http: HttpClient) { }

    listMusicsByCity(city: string): Observable<Music[]> {
        return this.http.get<Music[]>(environment.API_URL + '/recommendations?city=' + city);
    }
}
