import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';

@Injectable()
export class BasicAuthInterceptor implements HttpInterceptor {

    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const auth = window.btoa(environment.API_USERNAME + ':' + environment.API_PASSWORD);
        request = request.clone({
            setHeaders: { Authorization: `Basic ${auth}` }
        });

        return next.handle(request);
    }
}
