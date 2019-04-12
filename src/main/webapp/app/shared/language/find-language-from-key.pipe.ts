import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'findLanguageFromKey' })
export class FindLanguageFromKeyPipe implements PipeTransform {
    private languages: any = {
        'zh-tw': { name: '繁體中文' },
        en: { name: 'English' },
        fr: { name: 'Français' },
        de: { name: 'Deutsch' },
        it: { name: 'Italiano' },
        ja: { name: '日本語' },
        'pt-br': { name: 'Português (Brasil)' }
        // jhipster-needle-i18n-language-key-pipe - JHipster will add/remove languages in this object
    };
    transform(lang: string): string {
        return this.languages[lang].name;
    }
}